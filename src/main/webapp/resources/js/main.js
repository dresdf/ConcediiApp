$(document).ready(function () {
    $('#id').attr('readonly', true);
    $('#id').addClass('input-disabled');
    


});
function validateForm()
{
    if (document.frm.datastart.value > document.frm.datafinal.value)
    {
        alert("Data de inceput trebuie sa fie mai mica decat data de sfarsit");
        document.frm.datastart.focus();
        return false;
    }
}

function setHiddenId(val) {
    document.getElementById('hiddenid').value = val;
    alert('Cererea de concediu cu numarul ' + val + ' a fost aprobata');
}

function setHiddenIdReject(val) {
    document.getElementById('hiddenidreject').value = val;
    alert('Cererea de concediu cu numarul ' + val + ' a fost respinsa');
}




