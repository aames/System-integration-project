package Core.Workers;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 *
 * @author Andrew
 * This class controls the SYSTEM clipboard, not the local clipboard.
 */
public final class ClipboardControl implements ClipboardOwner {

    public ClipboardControl() {
    }

    public void setClipboard(String aString) {
        StringSelection stringSelection = new StringSelection(aString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, this);
    }
    /*
     * Might be useful.. 
     */
    public String getClipboard(){
        return "Unsupported";
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        throw new UnsupportedOperationException("Unsupported");
    }
}
