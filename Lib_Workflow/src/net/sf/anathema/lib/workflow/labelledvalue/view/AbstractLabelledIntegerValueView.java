package net.sf.anathema.lib.workflow.labelledvalue.view;

import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;

public abstract class AbstractLabelledIntegerValueView extends AbstractLabelledValueView implements
    ILabelledValueView<Integer> {

  protected static String createLengthString(int length) {
    String lengthString = "0"; //$NON-NLS-1$
    for (int index = 0; index < length - 1; index++) {
      lengthString = lengthString.concat("0"); //$NON-NLS-1$
    }
    return lengthString;
  }

  public AbstractLabelledIntegerValueView(String labelText, int value, boolean adjustFontSize) {
    super(labelText, value, adjustFontSize);
  }

  public AbstractLabelledIntegerValueView(String labelText, String sizeText, int value, boolean adjustFontSize) {
    super(labelText, String.valueOf(value), sizeText, adjustFontSize);
  }

  public abstract void addComponents(IGridDialogPanel dialogPanel);

  public void setValue(Integer value) {
    setText(String.valueOf(value));
  }
}