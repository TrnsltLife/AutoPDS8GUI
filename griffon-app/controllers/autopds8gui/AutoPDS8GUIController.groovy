package autopds8gui

import javax.swing.JFileChooser
import org.silcongo.autopds.app.ScriptProducerExcelExport
import StackTrace

class AutoPDS8GUIController {
    // these will be injected by Griffon
    def model
    def view

    void mvcGroupInit(Map args) {
        // this method is called after model and view are injected
    }
	
    def openAndRunMovieScript = { evt = null ->
		def openResult = view.fileChooserWindow.showOpenDialog()
		model.statusLog += "openResult is " + openResult.toString()
		if(JFileChooser.APPROVE_OPTION == openResult) {
			doOutside {
				File file = new File(view.fileChooserWindow.selectedFile.toString())
				runMovieScript(file)
			}
		}
    }
	
	def runMovieScript(file)
	{
		model.statusLog = ""
		doOutside
		{
			model.statusLog += "Opening file ${file.getCanonicalPath()}<br>\r\n"
			
			try
			{
				model.statusLog += "Begin processing...<br>\r\n"
				ScriptProducerExcelExport producer = new ScriptProducerExcelExport(file, "UTF-8")
				
				producer.infoLogHook = {message-> model.statusLog += """<font color="#000099">$message</font><br>\r\n"""}
				producer.errorLogHook = {message-> model.statusLog += """<font color="#ff0000">ERROR:<pre>$message</pre></font>"""}
				
				producer.processScript()
				
				model.statusLog += "Creating " + (new File(producer.settings.saveas)).getCanonicalPath() + "<br>\r\n"
				producer.createXMLFile()
				model.statusLog += """<font color="#009900">Done!</font><br>\r\n"""
			}
			catch(Exception e)
			{
				model.statusLog += """<font color="#ff0000">ERROR:<pre>${StackTrace.getStackTrace(e)}</pre></font>\r\n"""
			}
			model.statusLog += "<br>\r\n"
		}
	}

    def quit = { evt = null ->
		app.shutdown()
    }

    /*
    def action = { evt = null ->
    }
    */
}