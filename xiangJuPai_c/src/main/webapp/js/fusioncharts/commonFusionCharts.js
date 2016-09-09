var commonChartXml = " palette='2' divLineColor='ffffff' showBorder='1' borderColor='ffffff' canvasBorderColor='000000' canvasBorderThickness='3' formatNumberScale='0' ";
var commonStyleXml =  "<styles><definition>";
    commonStyleXml += "<style name='myCaptionFont' type='font' font='微软雅黑' size='14' color='666666' bold='1' underline='1'/>";
    commonStyleXml += "<style name='myShadow' type='Shadow' color='999999' angle='45'/>";
    commonStyleXml += "<style name='myGlow' type='Glow' color='FF5904'/>";
    commonStyleXml += "</definition><application>";
    commonStyleXml += "<apply toObject='Caption' styles='myCaptionFont,myShadow' />";
    commonStyleXml += "<apply toObject='SubCaption' styles='myShadow' />";
    commonStyleXml += "<apply toObject='XAxisName' styles='myGlow' />";
    commonStyleXml += "<apply toObject='YAxisName' styles='myGlow' />";
    commonStyleXml += "</application></styles> ";  
    
    