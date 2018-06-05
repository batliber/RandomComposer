package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import math.NoteMapper;
import media.MediaManager;
import theroy.Note;

public class Test extends JFrame {

	private static final long serialVersionUID = -4445152283944186768L;
	
	private MediaManager mediaManager = MediaManager.getInstance();
	private NoteMapper mapper = NoteMapper.getInstance();
	
	private Map<JCheckBox, Note> checkBoxMapper = new HashMap<JCheckBox, Note>();
	
	private boolean playing = false;
	private boolean lockVelocity = false;
	
	private int velocityRange = 2;
	private int currentNote;
	private int currentVelocity;
	
	private JPanel jPanelSettings = new JPanel();
	
//	private JComboBox jComboBoxInstruments;
	private JSpinner jSpinnerMinimumNoteRange = new JSpinner();
	private JSpinner jSpinnerMaximumNoteRange = new JSpinner();
	
	private JCheckBox jCheckBoxC = new JCheckBox("C", true);
	private JCheckBox jCheckBoxCS = new JCheckBox("C#", true);
	private JCheckBox jCheckBoxD = new JCheckBox("D", true);
	private JCheckBox jCheckBoxDS = new JCheckBox("D#", true);
	private JCheckBox jCheckBoxE = new JCheckBox("E", true);
	private JCheckBox jCheckBoxF = new JCheckBox("F", true);
	private JCheckBox jCheckBoxFS = new JCheckBox("F#", true);
	private JCheckBox jCheckBoxG = new JCheckBox("G", true);
	private JCheckBox jCheckBoxGS = new JCheckBox("G#", true);
	private JCheckBox jCheckBoxA = new JCheckBox("A", true);
	private JCheckBox jCheckBoxAS = new JCheckBox("A#", true);
	private JCheckBox jCheckBoxB = new JCheckBox("B", true);
	
	private JToggleButton jToggleButtonPlay = new JToggleButton(">");
	
	private JLabel jLabelX = new JLabel("X", SwingConstants.RIGHT);
	private JLabel jLabelY = new JLabel("Y");
	
	private PanelGestures panelGestures = new PanelGestures();
	
	public Test() {
		this.checkBoxMapper.put(jCheckBoxC, Note.C);
		this.checkBoxMapper.put(jCheckBoxCS, Note.CSharp);
		this.checkBoxMapper.put(jCheckBoxD, Note.D);
		this.checkBoxMapper.put(jCheckBoxDS, Note.DSharp);
		this.checkBoxMapper.put(jCheckBoxE, Note.E);
		this.checkBoxMapper.put(jCheckBoxF, Note.F);
		this.checkBoxMapper.put(jCheckBoxFS, Note.FSharp);
		this.checkBoxMapper.put(jCheckBoxG, Note.G);
		this.checkBoxMapper.put(jCheckBoxGS, Note.GSharp);
		this.checkBoxMapper.put(jCheckBoxA, Note.A);
		this.checkBoxMapper.put(jCheckBoxAS, Note.ASharp);
		this.checkBoxMapper.put(jCheckBoxB, Note.B);
		
//		this.jComboBoxInstruments = new JComboBox(new String[] {"1"});
//		this.jComboBoxInstruments.setSelectedIndex(0);
//		this.jComboBoxInstruments.addItemListener(new ItemListener(){
//			public void itemStateChanged(ItemEvent arg0) {
//				if (arg0.getStateChange() == ItemEvent.SELECTED) {
//					mediaManager.setInstrumentByNumber(jComboBoxInstruments.getSelectedIndex());
//				}
//			}
//		});
		
		this.jSpinnerMinimumNoteRange.setPreferredSize(new Dimension(40, 22));
//		this.jSpinnerMinimumNoteRange.setValue(mapper.getMinimumNoteRange());
		this.jSpinnerMinimumNoteRange.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
//				mapper.setMinimumNoteRange((Integer)jSpinnerMinimumNoteRange.getValue());
//				panelGestures.repaint();
			}
		});
		
		this.jSpinnerMaximumNoteRange.setPreferredSize(new Dimension(40, 22));
//		this.jSpinnerMaximumNoteRange.setValue(mapper.getMaximumNoteRange());
		this.jSpinnerMaximumNoteRange.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
//				mapper.setMaximumNoteRange((Integer)jSpinnerMaximumNoteRange.getValue());
//				panelGestures.repaint();
			}
		});
		
		this.panelGestures.setLayout(null);
		this.panelGestures.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent arg0) {
				if (playing) {
//					Point mouseLocation = panelGestures.getMousePosition();
//					
//					int newNote = mapper.getMapping(mouseLocation.x, 0, panelGestures.getWidth());
//					
//					int newVelocity = currentVelocity;
//					if (!lockVelocity)
//						newVelocity = (mouseLocation.y * velocityRange) / panelGestures.getHeight();
//					
//					if ((newNote != currentNote) || (newVelocity != currentVelocity)) {
//						mediaManager.noteOff(currentNote);
//						
//						currentNote = newNote;
//						currentVelocity = newVelocity;
//						
//						jLabelX.setText(Integer.toString(currentNote));
//						jLabelY.setText(Integer.toString(currentVelocity));
//						
//						mediaManager.noteOn(currentNote, currentVelocity);
//					}
				}
			}
		});
		
		this.jToggleButtonPlay.setPreferredSize(new Dimension(40, 25));
		this.jToggleButtonPlay.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (!playing && (e.getKeyCode() == KeyEvent.VK_CONTROL)) {
					Point mouseLocation = panelGestures.getMousePosition();
					
//					currentNote = mapper.getMapping(mouseLocation.x, 0, panelGestures.getWidth());
					currentVelocity = (mouseLocation.y * velocityRange) / panelGestures.getHeight();
				
					mediaManager.noteOn(currentNote, currentVelocity);
					
					playing = true;
				}
				
				if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
					lockVelocity = true;
					panelGestures.repaint();
				}
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					mediaManager.noteOff(0, currentNote);
				
					playing = false;
				}
				
				if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
					lockVelocity = false;
					panelGestures.repaint();
				}
			}
		});
		
		this.jLabelX.setPreferredSize(new Dimension(20, 22));
		this.jLabelY.setPreferredSize(new Dimension(20, 22));
		
		this.jCheckBoxC.addActionListener(new CheckBoxActionListener());
		this.jCheckBoxCS.addActionListener(new CheckBoxActionListener());
		this.jCheckBoxD.addActionListener(new CheckBoxActionListener());
		this.jCheckBoxDS.addActionListener(new CheckBoxActionListener());
		this.jCheckBoxE.addActionListener(new CheckBoxActionListener());
		this.jCheckBoxF.addActionListener(new CheckBoxActionListener());
		this.jCheckBoxFS.addActionListener(new CheckBoxActionListener());
		this.jCheckBoxG.addActionListener(new CheckBoxActionListener());
		this.jCheckBoxGS.addActionListener(new CheckBoxActionListener());
		this.jCheckBoxA.addActionListener(new CheckBoxActionListener());
		this.jCheckBoxAS.addActionListener(new CheckBoxActionListener());
		this.jCheckBoxB.addActionListener(new CheckBoxActionListener());
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		
		this.setLayout(new BorderLayout());
		
		this.jPanelSettings.setLayout(new FlowLayout());
		
//		this.jPanelSettings.add(this.jComboBoxInstruments);
		this.jPanelSettings.add(this.jSpinnerMinimumNoteRange);
		this.jPanelSettings.add(this.jSpinnerMaximumNoteRange);
		this.jPanelSettings.add(this.jToggleButtonPlay);
		this.jPanelSettings.add(this.jLabelX);
		this.jPanelSettings.add(this.jLabelY);
		this.jPanelSettings.add(this.jCheckBoxC);
		this.jPanelSettings.add(this.jCheckBoxCS);
		this.jPanelSettings.add(this.jCheckBoxD);
		this.jPanelSettings.add(this.jCheckBoxDS);
		this.jPanelSettings.add(this.jCheckBoxE);
		this.jPanelSettings.add(this.jCheckBoxF);
		this.jPanelSettings.add(this.jCheckBoxFS);
		this.jPanelSettings.add(this.jCheckBoxG);
		this.jPanelSettings.add(this.jCheckBoxGS);
		this.jPanelSettings.add(this.jCheckBoxA);
		this.jPanelSettings.add(this.jCheckBoxAS);
		this.jPanelSettings.add(this.jCheckBoxB);
		
		this.add(this.jPanelSettings, BorderLayout.NORTH);
		
		this.add(this.panelGestures, BorderLayout.CENTER);
		
		this.setBounds(0, 0, 800, 600);
		this.setVisible(true);
		
		this.jToggleButtonPlay.requestFocus();
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			new Test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private class CheckBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
//			Note note = checkBoxMapper.get(e.getSource());
			if (((JCheckBox)e.getSource()).isSelected()) {
//				mapper.addNote(note);
			} else {
//				mapper.removeNote(note);
			}
			panelGestures.repaint();
		}
	}
	
	private class PanelGestures extends JPanel {
		
		private static final long serialVersionUID = 399712442763424004L;

		public void paint(Graphics g) {
			super.paint(g);
			
			g.setColor(Color.GRAY);
			
			int notes = mapper.getNoteCount();
			int x = 0;
			for (int i = 1; i < notes; i++) {
				x += (this.getWidth() / notes) + 1;
				g.drawLine(x, 0, x, this.getHeight());
			}
			
			if (!lockVelocity) {
				int y = 0;
				for (int i = 1; i < velocityRange; i++) {
					y += (this.getHeight() / velocityRange) + 1;
					g.drawLine(0, y, this.getWidth(), y);
				}
			}
		}
	}
}