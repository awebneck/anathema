package net.sf.anathema.hero.charms.advance.costs;

import net.sf.anathema.character.main.magic.model.Magic;

public interface MagicCosts {

  int getMagicCosts(Magic magic, CostAnalyzer analyzer);
}
