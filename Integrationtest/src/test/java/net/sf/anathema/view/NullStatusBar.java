package net.sf.anathema.view;

import net.sf.anathema.framework.view.messaging.StatusBar;
import net.sf.anathema.lib.message.IBasicMessage;

public class NullStatusBar implements StatusBar {
  @Override
  public void setLatestMessage(IBasicMessage message) {
    //nothing to do
  }
}