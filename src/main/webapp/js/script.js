function validateForm()
{
    if (document.frm.id.value === "")
    {
        alert("Numele utilizatorului nu trebuie sa fie gol");
        document.frm.id.focus();
        return false;
    } else if (document.frm.password.value === "")
    {
        alert("Va rugam introduceti o parola pentru utilizator");
        document.frm.password.focus();
        return false;
    }
}

