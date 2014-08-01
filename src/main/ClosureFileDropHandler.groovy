import java.awt.datatransfer.*
import java.security.NoSuchAlgorithmException
import javax.swing.TransferHandler
import javax.swing.TransferHandler.DropLocation
import javax.swing.TransferHandler.TransferSupport

class ClosureFileDropHandler extends TransferHandler 
{
	Closure importFileClosure = {}
	
	ClosureFileDropHandler(Closure importFileClosure)
	{
		this.importFileClosure = importFileClosure
	}

	public boolean canImport(TransferSupport supp) 
	{
		/* return false if the drop doesn't contain a list of files */
		if(!supp.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) 
		{
			return false;
		}

		boolean copySupported = (COPY & supp.getSourceDropActions()) == COPY;

		if (copySupported) 
		{
			supp.setDropAction(COPY);
			return true;
		}

		return false;
	}

	public boolean importData(TransferSupport supp) 
	{
		if (!canImport(supp)) 
		{
			return false;
		}

		/* get the Transferable */
		Transferable t = supp.getTransferable();

		try 
		{

			Object data = t.getTransferData(DataFlavor.javaFileListFlavor);

			List fileList = (List)data;

			fileList.each{currentfile->
				importFileClosure.call(currentfile)
			}
		} 
		catch (UnsupportedFlavorException e) 
		{
			return false;
		} 
		catch (IOException e) 
		{
			return false;
		} 
		catch (NoSuchAlgorithmException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
}