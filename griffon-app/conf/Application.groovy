application {
    title = 'AutoPDS8GUI'
    startupGroups = ['AutoPDS8GUI']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "autoPDS8GUI"
    'AutoPDS8GUI' {
        model      = 'autopds8gui.AutoPDS8GUIModel'
        view       = 'autopds8gui.AutoPDS8GUIView'
        controller = 'autopds8gui.AutoPDS8GUIController'
    }

}
