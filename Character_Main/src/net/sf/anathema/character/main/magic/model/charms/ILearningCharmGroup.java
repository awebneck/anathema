package net.sf.anathema.character.main.magic.model.charms;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.model.charm.ICharmLearnListener;

public interface ILearningCharmGroup extends ICharmGroup, IBasicLearnCharmGroup {

  void toggleLearned(Charm charm);

  void addCharmLearnListener(ICharmLearnListener listener);

  Charm[] getCreationLearnedCharms();

  void learnCharm(Charm charm, boolean experienced);

  void learnCharmNoParents(Charm charm, boolean experienced, boolean announce);

  boolean isUnlearnable(Charm charm);

  boolean isUnlearnableWithoutConsequences(Charm charm);

  Charm[] getExperienceLearnedCharms();

  void forgetCharm(Charm child, boolean experienced);

  void forgetAll();

  boolean hasLearnedCharms();

  Charm[] getCoreCharms();

  void unlearnExclusives();

  void fireRecalculateRequested();
}