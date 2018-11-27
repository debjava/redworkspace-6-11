
Name "My Service Creation"

# General Symbol Definitions
!define REGKEY "SOFTWARE\$(^Name)"
!define VERSION 1.0
!define COMPANY "DDLAB INC, Bangalore, INDIA"
!define URL http://www.ddlabinc.com

!define NSISDIR246 "F:\javaworkspaces\redworkspace-6-11\service-launch4j\exebuilder\nsis-2.46"

# MUI Symbol Definitions
;!define MUI_ICON "${NSISDIR}\Contrib\Graphics\Icons\modern-install-colorful.ico"
!define MUI_ICON "F:\javaworkspaces\redworkspace-6-11\service-launch4j\exebuilder\appconfig\System-Install-3.ico"
!define MUI_FINISHPAGE_NOAUTOCLOSE
!define MUI_FINISHPAGE_RUN $INSTDIR\ServiceRunner.exe
# !define MUI_FINISHPAGE_RUN $INSTDIR\run.bat
;!define MUI_FINISHPAGE_SHOWREADME $INSTDIR\ReadMe.txt
;!define MUI_FINISHPAGE_RUN $INSTDIR\run.bat
!define MUI_UNICON "${NSISDIR246}\Contrib\Graphics\Icons\modern-uninstall-colorful.ico"
!define MUI_UNFINISHPAGE_NOAUTOCLOSE

# FOR Environment variables setting
!define ENV_VAR_NAME "MYSERVICE_HOME"
!define env_hklm 'HKLM "SYSTEM\CurrentControlSet\Control\Session Manager\Environment"'
!define env_hkcu 'HKCU "Environment"'

# Included files
!include Sections.nsh
!include MUI2.nsh

# Variables
Var StartMenuGroup

# Installer pages
!insertmacro MUI_PAGE_WELCOME
!insertmacro MUI_PAGE_DIRECTORY
!insertmacro MUI_PAGE_INSTFILES
!insertmacro MUI_PAGE_FINISH
!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES

# Installer languages
!insertmacro MUI_LANGUAGE English

# Installer attributes
OutFile Setup.exe
InstallDir "$PROGRAMFILES\MyServiceCreation"
CRCCheck on
XPStyle on
ShowInstDetails show
VIProductVersion 1.0.0.0
VIAddVersionKey ProductName "My Service Creation"
VIAddVersionKey ProductVersion "${VERSION}"
VIAddVersionKey CompanyName "${COMPANY}"
VIAddVersionKey CompanyWebsite "${URL}"
VIAddVersionKey FileVersion "${VERSION}"
VIAddVersionKey FileDescription ""
VIAddVersionKey LegalCopyright ""
InstallDirRegKey HKLM "${REGKEY}" Path
ShowUninstDetails show

# Installer sections
Section -Main SEC0000
    SetOutPath $INSTDIR
    SetOverwrite on
    File /r ..\dist\*
    WriteRegStr HKLM "${REGKEY}\Components" Main 1
	;MessageBox MB_OK $StartMenuGroup
	;MessageBox MB_OK $INSTDIR\servicesInv.exe
	;ExecWait $INSTDIR\servicesInv.exe
SectionEnd

Section -post SEC0001
    WriteRegStr HKLM "${REGKEY}" Path $INSTDIR
    SetOutPath $INSTDIR
    WriteUninstaller $INSTDIR\Uninstall.exe
    SetOutPath $SMPROGRAMS\$StartMenuGroup
    CreateShortcut "$SMPROGRAMS\$StartMenuGroup\Uninstall $(^Name).lnk" $INSTDIR\Uninstall.exe
    CreateShortcut "$SMPROGRAMS\$StartMenuGroup\ServiceRunner.lnk" $INSTDIR\ServiceRunner.exe
    ;CreateShortcut "$INSTDIR\Run.lnk" $INSTDIR\run.bat
    ;CreateShortcut "$SMPROGRAMS\$StartMenuGroup\Run.lnk" $INSTDIR\Run.lnk
	;CreateShortcut "$SMPROGRAMS\$StartMenuGroup\Run.lnk" $INSTDIR\run.bat
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" DisplayName "$(^Name)"
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" DisplayVersion "${VERSION}"
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" Publisher "${COMPANY}"
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" URLInfoAbout "${URL}"
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" DisplayIcon $INSTDIR\Uninstall.exe
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" UninstallString $INSTDIR\Uninstall.exe
    WriteRegDWORD HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" NoModify 1
    WriteRegDWORD HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" NoRepair 1
    ; set variable
    WriteRegExpandStr ${env_hklm} "${ENV_VAR_NAME}" $INSTDIR
    ; make sure windows knows about the change
    SendMessage ${HWND_BROADCAST} ${WM_WININICHANGE} 0 "STR:Environment" /TIMEOUT=5000
    ;ExecWait $INSTDIR\run.bat
    ;ExecWait '"$INSTDIR\run.bat"'
	;ExecWait $INSTDIR\servicesInv.exe
	;MessageBox MB_OK $StartMenuGroup
    
SectionEnd

# Macro for selecting uninstaller sections
!macro SELECT_UNSECTION SECTION_NAME UNSECTION_ID
    Push $R0
    ReadRegStr $R0 HKLM "${REGKEY}\Components" "${SECTION_NAME}"
    StrCmp $R0 1 0 next${UNSECTION_ID}
    !insertmacro SelectSection "${UNSECTION_ID}"
    GoTo done${UNSECTION_ID}
next${UNSECTION_ID}:
    !insertmacro UnselectSection "${UNSECTION_ID}"
done${UNSECTION_ID}:
    Pop $R0
!macroend

# Uninstaller sections
Section /o -un.Main UNSEC0000
    RmDir /r /REBOOTOK $INSTDIR
    DeleteRegValue HKLM "${REGKEY}\Components" Main
SectionEnd

Section -un.post UNSEC0001
    DeleteRegKey HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)"
    Delete /REBOOTOK "$SMPROGRAMS\$StartMenuGroup\Uninstall $(^Name).lnk"
    Delete /REBOOTOK $INSTDIR\Uninstall.exe
    DeleteRegValue HKLM "${REGKEY}" Path
    DeleteRegKey /IfEmpty HKLM "${REGKEY}\Components"
    DeleteRegKey /IfEmpty HKLM "${REGKEY}"
    RMDir /r /REBOOTOK $SMPROGRAMS\$StartMenuGroup
    ;RmDir /REBOOTOK $SMPROGRAMS\$StartMenuGroup
    RmDir /REBOOTOK $INSTDIR
    ; Deleting the environment variables
    DeleteRegValue ${env_hklm} "${ENV_VAR_NAME}"
    ; make sure windows knows about the change
    SendMessage ${HWND_BROADCAST} ${WM_WININICHANGE} 0 "STR:Environment" /TIMEOUT=5000
SectionEnd

# Installer functions
Function .onInit
    InitPluginsDir
    StrCpy $StartMenuGroup "MyServiceCreation"
	;ExecWait $INSTDIR\servicesInv.exe
    ;MessageBox MB_OK $StartMenuGroup
	;MessageBox MB_OK "IdealInvent Technologies"
FunctionEnd

# Uninstaller functions
Function un.onInit
    ReadRegStr $INSTDIR HKLM "${REGKEY}" Path
    StrCpy $StartMenuGroup "MyServiceCreation"
    !insertmacro SELECT_UNSECTION Main ${UNSEC0000}
FunctionEnd

