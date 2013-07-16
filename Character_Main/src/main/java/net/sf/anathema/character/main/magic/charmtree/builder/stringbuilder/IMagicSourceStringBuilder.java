package net.sf.anathema.character.main.magic.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.Magic;

public interface IMagicSourceStringBuilder<T extends Magic> {

  String createSourceString(T magic);

  String createShortSourceString(T charm);
}