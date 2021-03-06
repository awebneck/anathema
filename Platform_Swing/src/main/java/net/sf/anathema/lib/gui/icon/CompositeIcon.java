package net.sf.anathema.lib.gui.icon;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.model.AbstractChangeableModel;
import net.sf.anathema.lib.model.IChangeableModel;

import javax.swing.Icon;
import java.awt.Component;
import java.awt.Graphics;

public class CompositeIcon extends AbstractChangeableModel implements Icon {

  private final Icon[] icons;
  private final ChangeListener delegatingChangeListener = new ChangeListener() {
    @Override
    public void changeOccurred() {
      fireChangeEvent();
    }
  };

  public CompositeIcon(Icon... icons) {
    Preconditions.checkNotNull(icons);
    this.icons = icons;
    for (Icon icon : icons) {
      if (icon instanceof IChangeableModel) {
        ((IChangeableModel) icon).addChangeListener(delegatingChangeListener);
      }
    }
  }

  @Override
  public void paintIcon(Component c, Graphics g, int x, int y) {
    for (Icon icon : icons) {
      icon.paintIcon(c, g, x, y);
    }
  }

  @Override
  public int getIconWidth() {
    if (icons.length == 0) {
      return 0;
    }
    int width = icons[0].getIconWidth();
    for (int i = 1; i < icons.length; ++i) {
      if (icons[i].getIconWidth() > width) {
        width = icons[i].getIconWidth();
      }
    }
    return width;
  }

  @Override
  public int getIconHeight() {
    if (icons.length == 0) {
      return 0;
    }

    int height = icons[0].getIconHeight();
    for (Icon icon : icons) {
      if (icon.getIconHeight() > height) {
        height = icon.getIconHeight();
      }
    }
    return height;
  }
}