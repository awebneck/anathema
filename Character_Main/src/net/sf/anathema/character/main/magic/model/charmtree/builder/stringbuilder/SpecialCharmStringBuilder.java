package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.special.AbstractMultiLearnableCharm;
import net.sf.anathema.character.main.magic.model.charm.special.CharmTier;
import net.sf.anathema.character.main.magic.model.charm.special.EssenceFixedMultiLearnableCharm;
import net.sf.anathema.character.main.magic.model.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.main.magic.model.charm.special.TieredMultiLearnableCharm;
import net.sf.anathema.character.main.magic.model.charm.special.TraitDependentMultiLearnableCharm;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.Resources;

public class SpecialCharmStringBuilder implements IMagicTooltipStringBuilder {
  private static final String HtmlLineBreak = "<br>";
  private Resources resources;

  public SpecialCharmStringBuilder(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, Magic magic, Object specialDetails) {
    if (magic instanceof Charm && specialDetails instanceof ISpecialCharm) {
      Charm charm = (Charm) magic;
      ISpecialCharm details = (ISpecialCharm) specialDetails;
      StringBuilder specialCharmBuilder = new StringBuilder();
      if (details instanceof AbstractMultiLearnableCharm) {
        specialCharmBuilder.append(resources.getString("CharmTreeView.ToolTip.Repurchases"));
        specialCharmBuilder.append(TooltipBuilder.ColonSpace);
        if (details instanceof StaticMultiLearnableCharm) {
          printStaticLimit(specialCharmBuilder, (StaticMultiLearnableCharm) details);
        }
        if (details instanceof EssenceFixedMultiLearnableCharm) {
          return;
        }
        if (details instanceof TraitDependentMultiLearnableCharm) {
          printTraitLimit(specialCharmBuilder, (TraitDependentMultiLearnableCharm) details);
        }
        if (details instanceof TieredMultiLearnableCharm) {
          printTieredLimit(specialCharmBuilder, charm, (TieredMultiLearnableCharm) details);
        }
        specialCharmBuilder.append(HtmlLineBreak);
      }
      builder.append(specialCharmBuilder);
    }
  }

  private void printTieredLimit(StringBuilder builder, Charm charm, TieredMultiLearnableCharm details) {
    CharmTier[] tiers = details.getTiers();
    CharmTier first = tiers[0], second = tiers[1], last = tiers[tiers.length - 1];
    for (CharmTier tier : tiers) {
      if (tier == first) {
        continue;
      }
      if (tier == last && tier != second) {
        builder.append(resources.getString("CharmTreeView.ToolTip.Repurchases.And"));
        builder.append(TooltipBuilder.Space);
      }
      if (tier == second || tiers.length <= 3) {
        builder.append(resources.getString("Essence"));
        builder.append(TooltipBuilder.Space);
      }
      builder.append(tier.getRequirement(OtherTraitType.Essence));

      int traitRequirement = tier.getRequirement(charm.getPrimaryTraitType());
      if (traitRequirement > 0) {
        builder.append("/");
        if (tier == second || tiers.length <= 3) {
          builder.append(resources.getString(charm.getPrimaryTraitType().getId()));
          builder.append(TooltipBuilder.Space);
        }
        builder.append(traitRequirement);
      }
      if (tier != last) {
        builder.append(TooltipBuilder.CommaSpace);
      }
    }
  }

  private void printTraitLimit(StringBuilder builder, TraitDependentMultiLearnableCharm details) {
    builder.append("(");
    builder.append(resources.getString(details.getTraitType().getId()));
    if (details.getModifier() != 0) {
      builder.append(details.getModifier());
    }
    builder.append(")");
    builder.append(TooltipBuilder.Space);
    builder.append(resources.getString("CharmTreeView.ToolTip.Repurchases.Times"));
  }

  private void printStaticLimit(StringBuilder builder, StaticMultiLearnableCharm details) {
    builder.append(resources.getString("CharmTreeView.ToolTip.Repurchases.Static" + details.getAbsoluteLearnLimit()));
  }
}