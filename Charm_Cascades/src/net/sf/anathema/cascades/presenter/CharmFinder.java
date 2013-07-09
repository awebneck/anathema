package net.sf.anathema.cascades.presenter;

import com.google.common.base.Predicate;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.parser.charms.ICharmCache;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.util.Identifier;

import static net.sf.anathema.character.main.magic.model.charms.MartialArtsUtilities.MARTIAL_ARTS;

public class CharmFinder {

  private CharacterTypes characterTypes;
  private final ICharmCache cache;
  private final String id;

  public CharmFinder(CharacterTypes characterTypes, ICharmCache cache, String id) {
    this.characterTypes = characterTypes;
    this.cache = cache;
    this.id = id;
  }

  public Charm find() {
    Charm charm = searchCharmByCharacterType();
    if (charm != null) {
      return charm;
    }
    return searchCharmFromMartialArts();
  }

  private Charm searchCharmByCharacterType() {
    String[] idParts = id.split("\\.");
    try {
    	ICharacterType characterTypeId = characterTypes.findById(idParts[0]);
    	return findCharm(characterTypeId);
    }
    catch (IllegalArgumentException e) {
    	return null;
    }
  }

  private Charm searchCharmFromMartialArts() {
    return findCharm(MARTIAL_ARTS);
  }

  private Charm findCharm(Identifier treeType) {
    Charm[] charms = cache.getCharms(treeType);
    return ArrayUtilities.find(new Predicate<Charm>() {
      @Override
      public boolean apply(Charm candidate) {
        return candidate.getId().equals(id);
      }
    }, charms);
  }
}