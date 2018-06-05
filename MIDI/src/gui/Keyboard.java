package gui;

import instrument.Instrument;
import instrument.Piano;

import java.awt.SplashScreen;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import math.NoteMapper;
import media.MediaManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import utils.KeyboardMapper;

public class Keyboard {

	private int __OCTAVA = 2;
	
	private boolean[] alterations = new boolean[] { false, true, false, true, false, false, true, false, true, false, true, false };
	
	private MediaManager mediaManager = MediaManager.getInstance();
	private Instrument instrument = new Piano();
	private NoteMapper noteMapper = NoteMapper.getInstance();
	
	private Map<Integer, Boolean> notes = new HashMap<Integer, Boolean>();
	
	private Display display = new Display();
	private GridLayout gridLayoutShell;
	private Shell shell;
	
	private GridLayout gridLayoutKeyboard;
	private Composite compositeKeyboard;
	
	private Color colorBlack = new Color(display, 0, 0, 0);
	private Color colorWhite = new Color(display, 255, 255, 255);
	private Color colorRed = new Color(display, 255, 0, 0);
	
	private Map<Integer, Composite> compositesAlterations = new HashMap<Integer, Composite>();
	private Map<Integer, Collection<Label>> keys = new HashMap<Integer, Collection<Label>>();
	
	public Keyboard() {
		for (int i=0; i<42; i++) {
			notes.put((instrument.getTessitura().getMin() + 12 * __OCTAVA) + i, false);
		}
		
		this.display.addFilter(SWT.KeyDown, new Listener() {
			public void handleEvent(Event e) {
				// Nothing
			}
		});
        
		this.gridLayoutShell = new GridLayout(1, true);
		
		// Shell
		this.shell = new Shell(this.display);
        this.shell.setText("Teclado");
        this.shell.setSize(800, 450);
        this.shell.setLayout(this.gridLayoutShell);
        
        GlobalKeyboardHook globalKeyboardHook = new GlobalKeyboardHook();
        
        globalKeyboardHook.addKeyListener(new GlobalKeyAdapter() {
			public void keyPressed(GlobalKeyEvent event) {
				final int note = KeyboardMapper.map(__OCTAVA, event.getKeyChar());
				
//				System.out.println((int) event.getKeyChar());
				
				if (note >= 0 && !notes.get(note)) {
					mediaManager.noteOn(note, 0);
					
					notes.put(note, true);
					
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							for (Label label : keys.get(note)) {
								label.setBackground(colorRed);
							}
						}
					});
				}
			}

			public void keyReleased(GlobalKeyEvent event) {
				final int note = KeyboardMapper.map(__OCTAVA, event.getKeyChar());
				notes.put(note, false);
				
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						if (note >= 0) {
							for (Label label : keys.get(note)) {
								label.setBackground((Color) label.getData());
							}
						}
					}
				});
				
				mediaManager.noteOff(0, note);
			}
        });

        this.initializeInterface();
        
        this.shell.open();
		
		SplashScreen splashScreen = SplashScreen.getSplashScreen();
		if (splashScreen != null) {
			splashScreen.close();
		}
		
		while (!this.shell.isDisposed()) {
        	if (!this.display.readAndDispatch()) {
        		this.display.sleep();
        	}
        }
		
		this.display.dispose();
		System.exit(0);
	}
	
	private void initializeInterface() {
		this.gridLayoutKeyboard = new GridLayout(this.notes.size(), false);
		this.gridLayoutKeyboard.horizontalSpacing = 0;
		
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.FILL;
		
		this.compositeKeyboard = new Composite(this.shell, SWT.DOUBLE_BUFFERED);
		this.compositeKeyboard.setLayout(this.gridLayoutKeyboard);
		this.compositeKeyboard.setLayoutData(gridData);
		
		Integer[] sortedNotes = notes.keySet().toArray(new Integer[]{});
		
		Arrays.sort(sortedNotes);
		
		for (int i=0; i<sortedNotes.length; i++) {
			boolean alteration = this.alterations[i % this.alterations.length];
			
			Collection<Label> labels = new LinkedList<Label>();
			
			if (!alteration) {
				gridData = new GridData();
				gridData.grabExcessHorizontalSpace = true;
				gridData.horizontalAlignment = SWT.FILL;
				gridData.verticalAlignment = SWT.FILL;
				gridData.grabExcessVerticalSpace = true;
				
				if (i % 12 == 0 || i% 12 == 5) {
					Composite compositeNote = new Composite(this.compositeKeyboard, SWT.DOUBLE_BUFFERED);
					compositeNote.setLayoutData(gridData);
					
					GridLayout gridLayoutNote = new GridLayout(1, false);
					gridLayoutNote.marginBottom = 0;
					gridLayoutNote.marginHeight = 0;
					gridLayoutNote.marginLeft = 5;
					gridLayoutNote.marginRight = 0;
					gridLayoutNote.marginTop = 0;
					gridLayoutNote.marginWidth = 0;
					
					compositeNote.setLayout(gridLayoutNote);
					
					gridData = new GridData();
					gridData.grabExcessHorizontalSpace = true;
					gridData.horizontalAlignment = SWT.FILL;
					gridData.verticalAlignment = SWT.FILL;
					gridData.grabExcessVerticalSpace = true;
					
					Label labelKey = new Label(compositeNote, SWT.NONE);
					labelKey.setAlignment(SWT.CENTER);
					labelKey.setBackground(colorWhite);
					labelKey.setData(labelKey.getBackground());
					labelKey.setForeground(colorBlack);
					labelKey.setLayoutData(gridData);
					labelKey.setText(noteMapper.getReverseMapping(sortedNotes[i]).toString().replace("Sharp", "#"));
					
					labels.add(labelKey);
				} else {
					Label labelKey = new Label(this.compositeKeyboard, SWT.NONE);
					labelKey.setAlignment(SWT.CENTER);
					labelKey.setBackground(colorWhite);
					labelKey.setData(labelKey.getBackground());
					labelKey.setForeground(colorBlack);
					labelKey.setLayoutData(gridData);
					labelKey.setText(noteMapper.getReverseMapping(sortedNotes[i]).toString().replace("Sharp", "#"));
					
					labels.add(labelKey);
				}
			} else {
				GridLayout gridLayoutAlteration = new GridLayout(2, false);
				gridLayoutAlteration.marginBottom = 0;
				gridLayoutAlteration.marginHeight = 0;
				gridLayoutAlteration.marginLeft = 0;
				gridLayoutAlteration.marginRight = 0;
				gridLayoutAlteration.marginTop = 0;
				gridLayoutAlteration.marginWidth = 0;
				
				gridData = new GridData();
				gridData.grabExcessHorizontalSpace = true;
				gridData.horizontalAlignment = SWT.FILL;
				gridData.verticalAlignment = SWT.FILL;
				gridData.grabExcessVerticalSpace = true;
				
				Composite compositeAlteration = new Composite(this.compositeKeyboard, SWT.DOUBLE_BUFFERED);
				compositeAlteration.setLayout(gridLayoutAlteration);
				compositeAlteration.setLayoutData(gridData);
				
				compositesAlterations.put(sortedNotes[i], compositeAlteration);
			}
			
			keys.put(sortedNotes[i], labels);
		}
		
		for (int i=0; i<sortedNotes.length; i++) {
			boolean alteration = this.alterations[i % this.alterations.length];
			
			Collection<Label> labels = new LinkedList<Label>();
			
			if (alteration) {
				Composite compositeAlteration = compositesAlterations.get(sortedNotes[i]);
				
				gridData = new GridData();
				gridData.grabExcessHorizontalSpace = true;
				gridData.horizontalAlignment = SWT.FILL;
				gridData.horizontalSpan = 2;
				gridData.verticalAlignment = SWT.FILL;
				gridData.grabExcessVerticalSpace = true;
				
				Label labelKey = new Label(compositeAlteration, SWT.NONE);
				labelKey.setAlignment(SWT.CENTER);
				labelKey.setBackground(colorBlack);
				labelKey.setData(labelKey.getBackground());
				labelKey.setForeground(colorWhite);
				labelKey.setText(noteMapper.getReverseMapping(sortedNotes[i]).toString().replace("Sharp", "#"));
				labelKey.setLayoutData(gridData);
				
				labels.add(labelKey);
				
				gridData = new GridData();
				gridData.grabExcessHorizontalSpace = true;
				gridData.horizontalAlignment = SWT.FILL;
				gridData.verticalAlignment = SWT.FILL;
				gridData.grabExcessVerticalSpace = true;
				
				Label labelDummy = new Label(compositeAlteration, SWT.NONE);
				labelDummy.setBackground(colorWhite);
				labelDummy.setData(colorWhite);
				labelDummy.setLayoutData(gridData);
				
				Collection<Label> previous = keys.get(sortedNotes[i - 1]);
				previous.add(labelDummy);
				
				gridData = new GridData();
				gridData.grabExcessHorizontalSpace = true;
				gridData.horizontalAlignment = SWT.FILL;
				gridData.verticalAlignment = SWT.FILL;
				gridData.grabExcessVerticalSpace = true;
				
				labelDummy = new Label(compositeAlteration, SWT.NONE);
				labelDummy.setBackground(colorWhite);
				labelDummy.setData(colorWhite);
				labelDummy.setLayoutData(gridData);
				
				Collection<Label> next = keys.get(sortedNotes[i + 1]);
				next.add(labelDummy);
				
				keys.put(sortedNotes[i], labels);
			}
		}
	}
	
	public static void main(String[] args) {
		new Keyboard();
	}
}