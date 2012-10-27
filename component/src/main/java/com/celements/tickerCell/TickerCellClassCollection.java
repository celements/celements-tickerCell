package com.celements.tickerCell;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xwiki.component.annotation.Component;
import org.xwiki.model.reference.DocumentReference;

import com.celements.common.classes.AbstractClassCollection;
import com.xpn.xwiki.XWiki;
import com.xpn.xwiki.XWikiException;
import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.objects.classes.BaseClass;

@Component("com.celements.tickerCell.classcollection")
public class TickerCellClassCollection extends AbstractClassCollection {

  public static final String TICKERCELL_CLASS_DOC = "TickerCellClass";
  public static final String TICKER_CLASSES_SPACE = "Classes";

  private static Log LOGGER = LogFactory.getFactory().getInstance(
      TickerCellClassCollection.class);

  @Override
  protected Log getLogger() {
    return LOGGER;
  }

  public TickerCellClassCollection() {}

  @Override
  protected void initClasses() throws XWikiException {
    getTickerCellClass();
  }

  public String getConfigName() {
    return "celTickerCell";
  }

  public DocumentReference getTickerCellClassRef(String wikiName) {
    return new DocumentReference(wikiName, TICKER_CLASSES_SPACE, TICKERCELL_CLASS_DOC);
  }

  BaseClass getTickerCellClass() throws XWikiException {
    DocumentReference classRef = getTickerCellClassRef(getContext().getDatabase());
    XWikiDocument doc;
    XWiki xwiki = getContext().getWiki();
    boolean needsUpdate = false;
    
    try {
      doc = xwiki.getDocument(classRef, getContext());
    } catch (XWikiException exp) {
      LOGGER.error("Failed to get xClass document for [" + classRef + "].", exp);
      doc = new XWikiDocument(classRef);
      needsUpdate = true;
    }
    
    BaseClass bclass = doc.getXClass();
    bclass.setDocumentReference(classRef);
    
    needsUpdate |= bclass.addTextField("tickerSpace", "Ticker Space Name", 30);
    needsUpdate |= bclass.addBooleanField("showArchive", "Show Archive", "yesno");
    needsUpdate |= bclass.addTextField("columnConfig", "Column Configuration", 30);
    
    setContentAndSaveClassDocument(doc, needsUpdate);
    return bclass;
  }

}
