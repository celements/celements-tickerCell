package com.celements.tickerCell;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.xwiki.context.Execution;
import org.xwiki.model.reference.DocumentReference;

import com.celements.cells.attribute.AttributeBuilder;
import com.celements.common.classes.IClassCollectionRole;
import com.celements.pagetype.IPageTypeConfig;
import com.celements.pagetype.PageType;
import com.celements.pagetype.PageTypeReference;
import com.google.common.base.Optional;
import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.web.Utils;

public class TickerCellConfig implements IPageTypeConfig {

  private XWikiContext getContext() {
    return (XWikiContext) getExecution().getContext().getProperty("xwikicontext");
  }

  private Execution getExecution() {
    return Utils.getComponent(Execution.class);
  }

  @Override
  public boolean displayInFrameLayout() {
    return true;
  }

  @Override
  public List<String> getCategories() {
    return Arrays.asList("pageType", "celltype");
  }

  public PageTypeReference getPageTypeReference() {
    return new PageTypeReference(getName(), "com.celements.TickerCellPageTypeProvider",
        getCategories());
  }

  @Override
  public String getName() {
    return "TickerCell";
  }

  @Override
  public String getPrettyName() {
    return "Ticker Cell";
  }

  @Override
  public String getRenderTemplateForRenderMode(String renderMode) {
    if ("view".equals(renderMode)) {
      return resolveTemplate("Templates.TickerCellView");
    } else if ("edit".equals(renderMode)) {
      return resolveTemplate("Templates.TickerCellEdit");
    }
    return "";
  }

  private String resolveTemplate(String specView) {
    return new PageType("PageTypes.TickerCell").resolveTemplatePath(specView, getContext());
  }

  @Override
  public boolean hasPageTitle() {
    return false;
  }

  @Override
  public boolean isVisible() {
    return ((TickerCellClassCollection) Utils.getComponent(IClassCollectionRole.class,
        "com.celements.tickerCell.classcollection")).isActivated();
  }

  @Override
  public boolean isUnconnectedParent() {
    return false;
  }

  @Override
  public boolean useInlineEditorMode() {
    return false;
  }

  @Override
  @NotNull
  public Optional<String> defaultTagName() {
    return Optional.absent();
  }

  @Override
  public void collectAttributes(@NotNull AttributeBuilder attrBuilder,
      @NotNull DocumentReference cellDocRef) {
  }

}
