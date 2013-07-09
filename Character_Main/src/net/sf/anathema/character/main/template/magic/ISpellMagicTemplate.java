package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.character.main.magic.model.spells.CircleType;

public interface ISpellMagicTemplate {

  CircleType[] getSorceryCircles();

  CircleType[] getNecromancyCircles();

  boolean canLearnSorcery();

  boolean canLearnNecromancy();
  
  boolean canLearnSpellMagic();

  boolean knowsSorcery(Charm[] knownCharms);

  boolean knowsNecromancy(Charm[] knownCharms);
  
  boolean knowsSpellMagic(Charm[] knownCharms);
  
  boolean knowsSpellMagic(Charm[] knownCharms, CircleType circle);
  
  boolean canLearnSpell(ISpell spell, Charm[] knownCharms);
}