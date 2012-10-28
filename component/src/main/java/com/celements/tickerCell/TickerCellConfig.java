package com.celements.tickerCell;

import java.util.Arrays;
import java.util.List;

import com.celements.common.classes.IClassCollectionRole;
import com.celements.pagetype.IPageTypeConfig;
import com.celements.pagetype.PageTypeReference;
import com.xpn.xwiki.web.Utils;

public class TickerCellConfig implements IPageTypeConfig {

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
      return "Templates.TickerCellView";
    } else if ("edit".equals(renderMode)) {
      return "Templates.TickerCellEdit";
    }
    return "";
  }

  public boolean hasPageTitle() {
    return false;
  }

  public boolean isVisible() {
    return ((TickerCellClassCollection)Utils.getComponent(IClassCollectionRole.class,
        "com.celements.tickerCell.classcollection")).isActivated();
  }

}
