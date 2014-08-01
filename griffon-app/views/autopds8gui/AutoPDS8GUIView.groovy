package autopds8gui

import javax.swing.filechooser.FileFilter
import ClosureFileDropHandler

fileChooserWindow = fileChooser(fileFilter: [getDescription: {-> "*.txt"}, accept:{file-> file ==~ /.*?\.txt/ || file.isDirectory() }] as FileFilter)

closureFileDropHandler = new ClosureFileDropHandler({controller.runMovieScript(it)})
//closureFileDropHandler = new ClosureFileDropHandler({println(it)})

fileViewerWindow = application(title:'AutoPDS8GUI',
  size:[480,320],
  pack:false,
  //location:[50,50],
  locationByPlatform:true,
  iconImage: imageIcon('/AutoPDS8GUI-icon-48x48.png').image,
  iconImages: [imageIcon('/AutoPDS8GUI-icon-48x48.png').image,
               imageIcon('/AutoPDS8GUI-icon-32x32.png').image,
               imageIcon('/AutoPDS8GUI-icon-16x16.png').image]
) {
	actions
	{
		action(id:"openAction", name:"Run Movie Script...", mnemonic:"R", accelerator:shortcut("R"), closure:controller.openAndRunMovieScript)
		action(id:"quitAction", name:"Quit", mnemonic:"Q", accelerator:shortcut("Q"), closure:controller.quit)
	}
	menuBar {
		menu("File")
		{
			menuItem(openAction)
			separator()
			menuItem(quitAction)
		}
	}
	borderLayout()
	scrollPane()
	{
		textPane(id:"textPane", editable:false, contentType:"text/html; charset=UTF-8", text:bind{model.statusLog.toString()}, 
		dragEnabled:true,
		transferHandler:closureFileDropHandler
		)
	}
}
