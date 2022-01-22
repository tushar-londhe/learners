/**
 * 
 */

function enable(selCheckBox)
{
	if(selCheckBox.checked)
	{
		var checkboxes = document.getElementsByName('chkTeachers'+selCheckBox.value);
	
		for (var i=0, n=checkboxes.length;i<n;i++) 
		{    		
			document.getElementById(checkboxes[i]).removeAttribute("disabled");			
		}
	}
}

function enableClass(selCheckBox)
{
	if(selCheckBox.checked)
	{
		document.getElementById('sel'+selCheckBox.value).removeAttribute("disabled");
	}
	else
	{
		document.getElementById('sel'+selCheckBox.value).disabled = true;
		document.getElementById('sel'+selCheckBox.value).selectedIndex=0;		
	}	
}



function validateAndSubmit()
{
	var strSelStudents='';
	
	$("input:checkbox[name=chk]:checked").each(function(){
    	strSelStudents = strSelStudents + $(this).val() + ',';
	});
	
	document.getElementById('selStudents').value=strSelStudents;
}