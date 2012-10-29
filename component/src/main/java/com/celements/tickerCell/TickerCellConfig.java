package com.celements.tickerCell;

import java.util.Arrays;
import java.util.List;

import org.xwiki.context.Execution;

import com.celements.common.classes.IClassCollectionRole;
import com.celements.pagetype.IPageTypeConfig;
import com.celements.pagetype.PageType;
import com.celements.pagetype.PageTypeReference;
import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.web.Utils;

public class TickerCellConfig implements IPageTypeConfig {

  private XWikiContext getContext() {
    return (XWikiContext) getExecution().getContext().getProperty("xwikicontext");
  }

  private Execution getExecution() {
    return Utils.getComponent(Execution.class);
  }

  public boolean displayInFrameLayout() {
    return true;
  }

  public List<String> getCategories() {
    return Arrays.asList("pageType", "celltype");
  }

  public PageTypeReference getPageTypeReference() {
    return new PageTypeReference(getName(), "com.celements.TickerCellPageTypeProvider",
        getCategories());
  }

  public String getName() {
    return "TickerCell";
  }

  public String getPrettyName() {
    return "Ticker Cell";
  }

  public String getRenderTemplateForRenderMode(String renderMode) {
    if ("view".equals(renderMode)) {
      return resolveTemplate("Templates.TickerCellView");
    } else if ("edit".equals(renderMode)) {
      return resolveTemplate("Templates.TickerCellEdit");
    }
    return "";
  }

  private String resolveTemplate(String specView) {
    return new PageType("PageTypes.TickerCell").resolveTemplatePath(specView,
        getContext());
  }

  public boolean hasPageTitle() {
    return false;
  }

  public boolean isVisible() {
    return ((TickerCellClassCollection)Utils.getComponent(IClassCollectionRole.class,
        "com.celements.tickerCell.classcollection")).isActivated();
  }

  
}
