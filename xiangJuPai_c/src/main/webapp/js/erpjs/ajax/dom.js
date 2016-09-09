function getElementValue(root, elementName)
{
	var eleValue = null;
	/*logger.append(root);*/
	//logger.append("[GetElement][elementName]"+elementName);
	//alert("elementName="+elementName);
	var eleNode = root.getElementsByTagName(elementName.toUpperCase())[0];

	
	if(eleNode != null){
		if(eleNode.hasChildNodes()){
			eleValue = eleNode.firstChild.nodeValue;
		}
		//logger.append("[GetElement][elementValue]"+eleValue);
	}
	return eleValue;
}

function getFlagValue(textObj)
{	
	return getTextSubstr(textObj, "<FLAG>", "</FLAG>");
}

function getReturnData(textObj)
{
	return getTextSubstr(textObj, "<RETURNDATA>", "</RETURNDATA>");
}

function getTextSubstr(text, startText, endText)
{
	var startPos = text.indexOf(startText)+startText.length;
	var endPos   = text.indexOf(endText);
/*	logger.append("[StartPos]"+startPos);*/
/*	logger.append("[EndPos]"+endPos);*/
	var substr = text.substring(startPos, endPos);
/*	logger.append("[substring]"+substr); */
	return substr;   
}

function appendNode(parent,child)
{
	var pElement = document.getElementById(parent);
	pElement.appendChild(child); 
}


function removeNode(parent,child)
{
	var pElement = document.getElementById(parent);
	pElement.removeChild(child);
}