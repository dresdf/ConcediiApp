$(document).ready(function ()
{
    setInterval('updateClock()', 1000);
});

function updateClock( )
{
    var currentTime = new Date( );
    var currentHours = currentTime.getHours( );
    var currentMinutes = currentTime.getMinutes( );
    var currentSeconds = currentTime.getSeconds( );

    // Pad the minutes and seconds with leading zeros, if required
    currentMinutes = (currentMinutes < 10 ? "0" : "") + currentMinutes;
    currentSeconds = (currentSeconds < 10 ? "0" : "") + currentSeconds;

    // Choose either "AM" or "PM" as appropriate
    var timeOfDay = (currentHours < 12) ? "AM" : "PM";

    // Compose the string for display
    var currentTimeString = currentHours + ":" + currentMinutes + ":" + currentSeconds + " " + timeOfDay;

    // Refresh the DOM
    $("#clock").html(currentTimeString);

}
