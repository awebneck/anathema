package net.sf.anathema.hero.magic.sheet.content;

import com.google.common.base.Function;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charmtree.builder.MagicDisplayLabeler;
import net.sf.anathema.lib.resources.Resources;

public class CharmPrintNameTransformer implements Function<Charm, String> {

  private final MagicDisplayLabeler labeler;

  public CharmPrintNameTransformer(Resources resources) {
    this.labeler = new MagicDisplayLabeler(resources);
  }

  @Override
  public String apply(Charm input) {
    return labeler.getLabelForMagic(input);
  }
}
