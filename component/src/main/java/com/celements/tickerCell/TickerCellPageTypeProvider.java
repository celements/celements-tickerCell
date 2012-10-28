package com.celements.tickerCell;

import java.util.Arrays;
import java.util.List;

import org.xwiki.component.annotation.Component;

import com.celements.pagetype.IPageTypeConfig;
import com.celements.pagetype.IPageTypeProviderRole;
import com.celements.pagetype.PageTypeReference;

@Component("com.celements.TickerCellPageTypeProvider")
public class TickerCellPageTypeProvider implements IPageTypeProviderRole {

  private static final TickerCellConfig TICKER_CELL_CONFIG = new TickerCellConfig();

  public IPageTypeConfig getPageTypeByReference(PageTypeReference pageTypeRef) {
    if (pageTypeRef.equals(TICKER_CELL_CONFIG.getPageTypeReference())) {
      return TICKER_CELL_CONFIG;
    }
    return null;
  }

  public List<PageTypeReference> getPageTypes() {
    return Arrays.asList(TICKER_CELL_CONFIG.getPageTypeReference());
  }

}
