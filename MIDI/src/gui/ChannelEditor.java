package gui;

import java.util.Map;

import media.MediaManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ChannelEditor extends Shell {

	private GridLayout gridLayoutShell;
	
	public ChannelEditor(Display display, int style) {
		this.setText("Random composer :: Editor de canales");
        this.setSize(390, 500);
		
		initializeInterface();
	}
	
	protected void checkSubclass() {
		
	}

	public void initializeInterface() {
		this.gridLayoutShell = new GridLayout(2, false);
		this.gridLayoutShell.marginBottom = 5;
		this.gridLayoutShell.marginHeight = 0;
		this.gridLayoutShell.marginLeft = 5;
		this.gridLayoutShell.marginRight = 5;
		this.gridLayoutShell.marginTop = 5;
		this.gridLayoutShell.marginWidth = 0;
		
		this.setLayout(this.gridLayoutShell);
		
		Map<Integer, String> instruments = MediaManager.getInstance().listInstruments();
		String[] instrumentNames = instruments.values().toArray(new String[] {});
		
		for (Integer i : MediaManager.getInstance().listChannels()) {
			GridData gridData = new GridData();
			gridData.verticalAlignment = GridData.FILL;
			
			Label label = new Label(this, SWT.NONE);
			label.setText("Canal " + i + ":");
			label.setLayoutData(gridData);
			
			gridData = new GridData();
			gridData.horizontalAlignment = GridData.FILL;
			gridData.grabExcessHorizontalSpace = true;
			gridData.verticalAlignment = GridData.FILL;
			
			Combo comboInstruments = new Combo(this, SWT.NONE);
			comboInstruments.setItems(instrumentNames);
			comboInstruments.setData(i);
			comboInstruments.select(MediaManager.getInstance().getChannelInstrument(i));
			comboInstruments.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					Combo combo = ((Combo)e.getSource());
					
					int channelNumber = (int)combo.getData();
					int instrumentNumber = combo.getSelectionIndex();
					
					MediaManager.getInstance().setChannelInstrument(
						channelNumber,
						instrumentNumber
					);
				}
			});
			comboInstruments.setLayoutData(gridData);
		}
	}
}