package net.sf.anathema.hero.magic.display.coloring;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.display.view.charmtree.CharmGroupInformer;

public class ExternalPrerequisitesColorer implements CharmColorer {
  private final CharmGroupInformer groupInformer;
  private final CharmColoring coloring;

  public ExternalPrerequisitesColorer(CharmGroupInformer groupInformer, CharmColoring coloring) {
    this.groupInformer = groupInformer;
    this.coloring = coloring;
  }

  public void color(Charm charm) {
    for (Charm prerequisite : charm.getRenderingPrerequisiteCharms()) {
      if (isPartOfCurrentGroup(prerequisite)) {
        return;
      }
      coloring.colorCharm(prerequisite);

    }
  }

  private boolean isPartOfCurrentGroup(Charm charm) {
    return groupInformer.getCurrentGroup().isCharmFromGroup(charm);
  }
}