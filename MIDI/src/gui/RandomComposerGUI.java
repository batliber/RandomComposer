package gui;

import instrument.InstrumentFactory;
import instrument.InstrumentType;

import java.awt.SplashScreen;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import playback.PlaybackManager;
import theroy.CompositionEventData;
import theroy.CompositionManager;
import theroy.CompositionTonalityChangeListener;
import theroy.Mode;
import theroy.Note;
import theroy.Stage;
import timing.FlowEventData;
import timing.FlowManager;
import timing.FlowStageChangeListener;
import timing.ProgressionManager;

import composition.BaseVoice;
import composition.VoiceFactory;
import composition.VoiceType;

public class RandomComposerGUI {
	
	private PlaybackManager playbackManager = PlaybackManager.getInstance();
	private CompositionManager compositionManager = CompositionManager.getInstance();
	private FlowManager flowManager = FlowManager.getInstance();
	
	private Display display = new Display();
	private GridLayout gridLayoutShell = new GridLayout(1, true);
	private Shell shell;
	
	private Composite compositePlayback;
	private GridLayout gridLayoutPlayback;
	
	private Button buttonPlay;
	private Button buttonStop;
	
	private Label labelInitialTonality;
	private Combo comboInitialTonality;
	
	private Label labelStage;
	private Combo comboStage;
	
	private Button buttonCompositionEditor;
	private Button buttonAddTrack;
	private Button buttonChannelEditor;
	private Button buttonProgressionEditor;
	
	private CompositionEditor compositionEditor;
	private ChannelEditor channelEditor;
	private ProgressionEditor progressionEditor;
	
	private Composite compositeMixer;
	private GridLayout gridLayoutMixer;
	
	public RandomComposerGUI() {
		this.shell = new Shell(this.display);
        this.shell.setText("Random composer :: By Líber Batalla");
        this.shell.setSize(805, 450);
        this.shell.setLayout(this.gridLayoutShell);
        
        this.initializeInterface();
        
        this.playbackManager.init();
        this.compositionManager.setKey(
        	Note.values()[this.comboInitialTonality.getSelectionIndex()]
        );
        this.compositionManager.setMode(
        	Mode.values()[this.comboInitialTonality.getSelectionIndex()]
        );
        this.compositionManager.addTonalityChangeListener(new CompositionTonalityChangeListener() {
			public void tonalityChanged(CompositionEventData compositionEventData) {
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						int i=0;
						
						for (String item : ((ArrayList<String>)comboInitialTonality.getData())) {
							if (item.equals(compositionManager.getKey() + " " + compositionManager.getMode())) {
								break;
							}
							i++;
						}
						
						comboInitialTonality.select(i);
					}
				});
			}
        });
        this.flowManager.addStageChangeListener(new FlowStageChangeListener() {
			public void stageChanged(FlowEventData flowEventData) {
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						int i=0;
						
						for (String item : ((ArrayList<String>)comboStage.getData())) {
							if (item.equals(ProgressionManager.getInstance().getCurrentStage().toString())) {
								break;
							}
							i++;
						}
						
						comboStage.select(i);
					}
				});
			}
        });
        
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
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		
		this.gridLayoutPlayback = new GridLayout(11, false);
		this.gridLayoutPlayback.marginBottom = 0;
		this.gridLayoutPlayback.marginHeight = 0;
		this.gridLayoutPlayback.marginLeft = 0;
		this.gridLayoutPlayback.marginRight = 0;
		this.gridLayoutPlayback.marginTop = 0;
		this.gridLayoutPlayback.marginWidth = 0;
		
		this.compositePlayback = new Composite(this.shell, SWT.DOUBLE_BUFFERED);
		this.compositePlayback.setLayout(gridLayoutPlayback);
		this.compositePlayback.setLayoutData(gridData);
		
		this.initializeCompositePlayback();
		
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		
		this.gridLayoutMixer = new GridLayout(1, false);
		this.gridLayoutMixer.marginBottom = 0;
		this.gridLayoutMixer.marginHeight = 0;
		this.gridLayoutMixer.marginLeft = 0;
		this.gridLayoutMixer.marginRight = 0;
		this.gridLayoutMixer.marginTop = 0;
		this.gridLayoutMixer.marginWidth = 0;
		
		this.compositeMixer = new Composite(this.shell, SWT.DOUBLE_BUFFERED);
		this.compositeMixer.setLayout(gridLayoutMixer);
		this.compositeMixer.setLayoutData(gridData);
		
		this.initializeCompositeMixer();
	}
	
	private void initializeCompositePlayback() {
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		this.buttonPlay = new Button(this.compositePlayback, SWT.NONE);
		this.buttonPlay.setText(">");
		this.buttonPlay.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				buttonStop.setEnabled(true);
				buttonPlay.setEnabled(false);
				comboInitialTonality.setEnabled(false);
				comboStage.setEnabled(false);
				buttonCompositionEditor.setEnabled(false);
				buttonAddTrack.setEnabled(false);
				buttonProgressionEditor.setEnabled(false);
				
				for (Control control : compositeMixer.getChildren()) {
					if (control instanceof Composite) {
						Composite composite = (Composite) control;
						for (Control child : composite.getChildren()) {
							if (child instanceof Button) {
								Button button = (Button)child;
								
								if (!button.getText().equals("M")
									&& !button.getText().equals("S")) {
									child.setEnabled(false);
								}
							} else {
								child.setEnabled(false);
							}
						}
					}
				}
				
				playbackManager.play();
			}
		});
		this.buttonPlay.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		this.buttonStop = new Button(this.compositePlayback, SWT.NONE);
		this.buttonStop.setText("||");
		this.buttonStop.setEnabled(false);
		this.buttonStop.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				buttonPlay.setEnabled(true);
				buttonStop.setEnabled(false);
				comboInitialTonality.setEnabled(true);
				comboStage.setEnabled(true);
				buttonCompositionEditor.setEnabled(false);
				buttonAddTrack.setEnabled(true);
				buttonProgressionEditor.setEnabled(true);
				
				for (Control control : compositeMixer.getChildren()) {
					if (control instanceof Composite) {
						Composite composite = (Composite) control;
						for (Control child : composite.getChildren()) {
							child.setEnabled(true);
						}
					}
				}
				
				playbackManager.stop();
			}
		});
		this.buttonStop.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		this.labelInitialTonality = new Label(this.compositePlayback, SWT.NONE);
		this.labelInitialTonality.setText("Tonalidad:");
		this.labelInitialTonality.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		ArrayList<String> items = new ArrayList<String>();
		for (Note note : Note.values()) {
			for (Mode mode : Mode.values()) {
				items.add(note + " " + mode);
			}
		}
		
		this.comboInitialTonality = new Combo(this.compositePlayback, SWT.NONE);
		this.comboInitialTonality.setItems(items.toArray(new String[] {}));
		this.comboInitialTonality.setData(items);
		this.comboInitialTonality.select(0);
		this.comboInitialTonality.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String comboValue = 
					comboInitialTonality.getItem(
						comboInitialTonality.getSelectionIndex()
					);
				
				Note note = 
					Note.valueOf(
						comboValue.substring(0, comboValue.indexOf(" "))
					);
				
				Mode mode = 
					Mode.valueOf(
						comboValue.substring(comboValue.indexOf(" ") + 1, comboValue.length())
					);
				
				CompositionManager.getInstance().setKey(note);
				CompositionManager.getInstance().setMode(mode);
			}
		});
		this.comboInitialTonality.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		this.labelStage = new Label(this.compositePlayback, SWT.NONE);
		this.labelStage.setText("Estado:");
		this.labelStage.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		items = new ArrayList<String>();
		for (Stage stage : Stage.values()) {
			items.add(stage.name());
		}
		
		this.comboStage = new Combo(this.compositePlayback, SWT.NONE);
		this.comboStage.setItems(items.toArray(new String[] {}));
		this.comboStage.setData(items);
		this.comboStage.select(0);
		this.comboStage.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
//				String comboStageValue = 
//					comboStage.getItem(
//						comboStage.getSelectionIndex()
//					);
				
//				Stage stage = 
//					Stage.valueOf(
//						comboStageValue
//					);
			}
		});
		this.comboStage.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		this.buttonCompositionEditor = new Button(this.compositePlayback, SWT.NONE);
		this.buttonCompositionEditor.setText("Composición...");
		this.buttonCompositionEditor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				buttonCompositionEditor.setEnabled(false);
				compositionEditor = new CompositionEditor(display, SWT.SHELL_TRIM);
				compositionEditor.addDisposeListener(new DisposeListener() {
					public void widgetDisposed(DisposeEvent arg0) {
						if (!buttonCompositionEditor.isDisposed()) {
							buttonCompositionEditor.setEnabled(true);
						}
					}					
				});
				
				compositionEditor.open();
			}
		});
		this.buttonCompositionEditor.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		this.buttonAddTrack = new Button(this.compositePlayback, SWT.NONE);
		this.buttonAddTrack.setText("Agregar pista");
		this.buttonAddTrack.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				addTrack();
			}
		});
		this.buttonAddTrack.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		this.buttonChannelEditor = new Button(this.compositePlayback, SWT.NONE);
		this.buttonChannelEditor.setText("Canales...");
		this.buttonChannelEditor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				buttonChannelEditor.setEnabled(false);
				channelEditor = new ChannelEditor(display, SWT.SHELL_TRIM);
				channelEditor.addDisposeListener(new DisposeListener() {
					public void widgetDisposed(DisposeEvent arg0) {
						if (!buttonChannelEditor.isDisposed()) {
							buttonChannelEditor.setEnabled(true);
						}
					}					
				});
				
				channelEditor.open();
			}
		});
		this.buttonChannelEditor.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		this.buttonProgressionEditor = new Button(this.compositePlayback, SWT.NONE);
		this.buttonProgressionEditor.setText("Progresiones...");
		this.buttonProgressionEditor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				progressionEditor = new ProgressionEditor(display, SWT.SHELL_TRIM);
				buttonProgressionEditor.setEnabled(false);
				progressionEditor.addDisposeListener(new DisposeListener() {
					public void widgetDisposed(DisposeEvent arg0) {
						if (!buttonProgressionEditor.isDisposed()) {
							buttonProgressionEditor.setEnabled(true);
						}
					}					
				});
				
				progressionEditor.open();
			}
		});
		this.buttonProgressionEditor.setLayoutData(gridData);
	}
	
	private void initializeCompositeMixer() {
		
	}
	
	private void addTrack() {
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		
		GridLayout gridLayout = new GridLayout(8, false);
		gridLayout.marginBottom = 0;
		gridLayout.marginHeight = 0;
		gridLayout.marginLeft = 0;
		gridLayout.marginRight = 0;
		gridLayout.marginTop = 0;
		gridLayout.marginWidth = 0;
		
		Composite compositeTrack = new Composite(this.compositeMixer, SWT.DOUBLE_BUFFERED);
		compositeTrack.setLayout(gridLayout);
		compositeTrack.setData(this.compositeMixer.getChildren().length);
		compositeTrack.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		Button button = new Button(compositeTrack, SWT.TOGGLE);
		button.setText("M");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				for (Control control : ((Button)e.getSource()).getParent().getChildren()) {
					if ((control instanceof Button) 
						&& ((((Button)control).getText()).equals("M"))) {
						
					} else {
						control.setEnabled(!((Button)e.getSource()).getSelection());
					}
				}
			}
		});
		button.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		button = new Button(compositeTrack, SWT.TOGGLE);
		button.setText("S");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				for (Control control : compositeMixer.getChildren()) {
					if (control instanceof Composite) {
						Composite composite = (Composite)control;
						if (!composite.equals(((Control)e.getSource()).getParent())) {
							Control[] children = composite.getChildren();
							
							boolean s = false;
							for (Control child : children) {
								if ((child instanceof Button) 
									&& ((((Button)child).getText()).equals("S"))
									&& ((((Button)child).getSelection()))) {
									s = true;
									break;
								}
							}
							
							if (!s) {
								for (Control child : children) {
									if ((child instanceof Button) 
										&& ((((Button)child).getText()).equals("S"))) {
										
									} else {
										child.setEnabled(!((Button)e.getSource()).getSelection());
									}
								}
							}
						} else {
							Control[] children = composite.getChildren();
							
							for (Control child : children) {
								child.setEnabled(((Button)e.getSource()).getSelection());
							}
						}
					}
				}
			}
		});
		button.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		Label label = new Label(compositeTrack, SWT.NONE);
		label.setText("Nombre:");
		label.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		
		Text text = new Text(compositeTrack, SWT.BORDER);
		text.setData("textNombre");
		text.setText("Voz " + Integer.toString((int)compositeTrack.getData()));
		text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					((Text)e.getSource()).setEnabled(false);
				}
			}
		});
		text.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		label = new Label(compositeTrack, SWT.NONE);
		label.setText("Instrumento:");
		label.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		ArrayList<String> items = new ArrayList<String>();
		for (InstrumentType instrumentType : InstrumentType.values()) {
			items.add(instrumentType.name());
		}
		
		Combo comboInstrument = new Combo(compositeTrack, SWT.NONE);
		comboInstrument.setItems(items.toArray(new String[] {}));
		comboInstrument.setData(items);
		comboInstrument.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		label = new Label(compositeTrack, SWT.NONE);
		label.setText("Estrategia:");
		label.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		items = new ArrayList<String>();
		for (VoiceType voiceType : VoiceType.values()) {
			items.add(voiceType.name());
		}
		
		Combo comboStrategy = new Combo(compositeTrack, SWT.NONE);
		comboStrategy.setItems(items.toArray(new String[] {}));
		comboStrategy.setData(items);
		comboStrategy.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Combo combo = (Combo)e.getSource();
				
				String comboValue = 
					combo.getItem(
						combo.getSelectionIndex()
					);
				
				VoiceType voiceType = 
					VoiceType.valueOf(comboValue);
				
				String comboInstrumentValue = 
					comboInstrument.getItem(
						comboInstrument.getSelectionIndex()
					);
				
				InstrumentType instrumentType = 
					InstrumentType.valueOf(comboInstrumentValue);
				
				int channelId = (int)combo.getParent().getData() - 1;
				String voiceId = Integer.toString((int)combo.getParent().getData());
				
				Text textNombre = null;
				for (Control control : combo.getParent().getChildren()) {
					if ((control instanceof Text)
						&& (((String)((Text)control).getData()).equals("textNombre"))) {
						textNombre = (Text)control;
					}
				}
				
				String voiceName = textNombre.getText();
				
				PlaybackManager.getInstance().removeVoice(voiceId);
				
				BaseVoice voice =
					VoiceFactory.getInstance().buildVoice(
						voiceType,
						InstrumentFactory.getInstance().buildInstrument(instrumentType),
						channelId,
						voiceId,
						voiceName
					);
				
				PlaybackManager.getInstance().addVoice(
					voice
				);
			}
		});
		comboStrategy.setLayoutData(gridData);
		
		this.compositeMixer.layout();
	}
	
	public static void main(String[] args) {
		new RandomComposerGUI();
	}
}