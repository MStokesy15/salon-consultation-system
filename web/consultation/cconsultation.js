//Execute on page load
$(function () {
    //Variable declaration
    var pageIndex = 1; //page index tracking
    var currentPage = 1; //current page in consultationPages
    var maxPages; //max number of page indices

    //Define pages associated with each consultation type - the same for evaluation
    var consultationTypes = {"Highlights": [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16],
        "Root Regrowth": [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16],
        "Full Head Colour": [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16],
        "Balayage": [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]};
    var consultationPages = []; //list of pages associated with selected consultation type

    //Load the ColourConsultationWizard html file for loading pages from
    $.get('../consultation/ColourConsultationWizard.html', function (data)
    {
        /*
         * count the number of pages in the source file
         * then initialise page display.
         */
        var a = $(data).find(".pagediv");
        maxPages = a.length;
        $('#previousButton').hide();
        $('#nextButton').hide();
        $('#finishButton').hide();
        $('#formColour\\:clientPhoto').hide();
        updateDisplay(1);
    });

    /*
     * Bind an event to the #consultation div
     * to enable use of enter button to proceed.
     */
    $('#consultation').bind('keydown', function (event) {
        //console.log("Div-event: " + event.keyCode);
        if (event.keyCode === 13 && pageIndex !== 1) {
            $('#nextButton').click();
        }
    });

    /**
     * Injects page content where div = #consultation.
     * @param {int} pageNum
     * @returns {undefined}
     */
    function updateDisplay(pageNum) {
        $('#consultation').load('../consultation/ColourConsultationWizard.html #p' + pageNum);
    }

    /**
     * Decides, based on the current page, what action(s) should be taken and
     * what page should be loaded from consultationPages.
     * @param {String} argin Optional argument
     * @returns {undefined}
     */
    this.nextPage = function (argin) {
        if (pageIndex < maxPages || pageIndex === 1) { //Find number of page elements... give them a custom class?
            if (currentPage === 1) { //Set the serviceType JSF variable
                if (typeof argin !== 'undefined') {
                    $('#formColour\\:serviceType').val(argin);
                }
                consultationPages = consultationTypes[$('#formColour\\:serviceType').val()];
                maxPages = consultationPages.length + 1;
            }
            if (currentPage === 2) { //Set the appointmentDate JSF variable
                if ($('#aptDate').val() === "") {
                    alert("You must enter an appointment date!");
                    return;
                } else {
                    //console.log($('#aptDate').val());
                    $('#formColour\\:appointmentDate').val($('#aptDate').val());
                }
            }
            if (currentPage === 3) { //Set the clientLikes JSF variable
                var likesList = [];
                if (document.getElementById("likeLength").checked === true) {
                    likesList.push("Length");
                }
                if (document.getElementById("likeVolume").checked === true) {
                    likesList.push("Volume");
                }
                if (document.getElementById("likeSmoothness").checked === true) {
                    likesList.push("Smoothness");
                }
                if (document.getElementById("likeCurls").checked === true) {
                    likesList.push("Curls");
                }
                if (document.getElementById("likeColour").checked === true) {
                    likesList.push("Colour");
                }
                if (document.getElementById("likeMovement").checked === true) {
                    likesList.push("Movement");
                }
                if (document.getElementById("likeThickness").checked === true) {
                    likesList.push("Thickness");
                }
                if (document.getElementById("likeEasyToStyle").checked === true) {
                    likesList.push("Easy To Style");
                }
                if (likesList.length === 0) {
                    return;
                } else {
                    $('#formColour\\:clientLikes').val(likesList);
                }
            }
            if (currentPage === 4) { //Set the clientDislikes JSF variable
                var dislikesList = [];
                if (document.getElementById("dislikeDryness").checked === true) {
                    dislikesList.push("Dryness");
                }
                if (document.getElementById("dislikeSplitEnds").checked === true) {
                    dislikesList.push("Split Ends");
                }
                if (document.getElementById("dislikeDull").checked === true) {
                    dislikesList.push("Dull");
                }
                if (document.getElementById("dislikeLackOfVolume").checked === true) {
                    dislikesList.push("Lack of Volume");
                }
                if (document.getElementById("dislikeStyleDoesNotLast").checked === true) {
                    dislikesList.push("Style Does Not Last");
                }
                if (document.getElementById("dislikeDamage").checked === true) {
                    dislikesList.push("Damage");
                }
                if (document.getElementById("dislikeFrizzy").checked === true) {
                    dislikesList.push("Frizzy");
                }
                if (document.getElementById("dislikeOily").checked === true) {
                    dislikesList.push("Oily");
                }
                if (document.getElementById("dislikeThickness").checked === true) {
                    dislikesList.push("Thickness");
                }
                if (dislikesList.length === 0) {
                    return;
                } else {
                    $('#formColour\\:clientDislikes').val(dislikesList);
                }
            }
            if (currentPage === 5) { //Set the clientHomeStyle JSF variable
                if ($('#homeStyle').val() === "") {
                    return;
                } else {
                    $('#formColour\\:clientHomeStyle').val($('#homeStyle').val());
                }
            }
            if (currentPage === 6) { //Set the clientHomeProducts JSF variable
                if ($('#homeProducts').val() === "") {
                    return;
                } else {
                    $('#formColour\\:clientHomeProducts').val($('#homeProducts').val());
                }
            }
            if (currentPage === 7) { //Set the clientLikesStyles JSF variable
                if ($('#likesStyles').val() === "") {
                    return;
                } else {
                    $('#formColour\\:clientLikesStyles').val($('#likesStyles').val());
                }
            }
            if (currentPage === 8) { //Set the clientPrevServices JSF variable
                if ($('#prevServices').val() === "") {
                    return;
                } else {
                    $('#formColour\\:clientPrevServices').val($('#prevServices').val());
                }
            }
            if (currentPage === 9) { //Set the clientAllergies JSF variable
                if ($('#allergies').val() === "") {
                    return;
                } else {
                    $('#formColour\\:clientAllergies').val($('#allergies').val());
                }
            }
            if (currentPage === 10) { //Sets the clientLastColour JSF variable
                if (typeof argin !== 'undefined') {
                    $('#formColour\\:clientLastColour').val(argin);
                    //console.log(argin);
                    if (argin !== "Never") { //Redefine consultationPages
                        // to remove page 11 and 12 as they are not relevant
                        // if the client has never had their hair coloured.
                        var newConsultationPages = [];
                        consultationPages.forEach(function (value) {
                            if (value !== 11 && value !== 12) {
                                newConsultationPages.push(value);
                            }
                        });
                        consultationPages = newConsultationPages;
                        //console.log(consultationPages);
                        maxPages = consultationPages.length + 1;
                    }
                } else {
                    if ($('#p10_comments').val() === "") {
                        return;
                    } else {
                        $('#formColour\\:clientLastColour').val('Other: ' + $('#p10_comments').val());
                    }
                }
            }
            if (currentPage === 11) { //Set clientColourWasLike JSF variable
                if ($('#colourWasLike').val() === "") {
                    return;
                } else {
                    $('#formColour\\:clientColourWasLike').val($('#colourWasLike').val());
                }
            }
            if (currentPage === 12) { //Set clientColourProblems JSF variable
                if ($('#colourProblems').val() === "") {
                    return;
                } else {
                    $('#formColour\\:clientColourProblems').val($('#colourProblems').val());
                }
            }
            if (currentPage === 13) { //Set clientLikesTones JSF variable
                if (typeof argin !== 'undefined') {
                    $('#formColour\\:clientLikesTones').val(argin);
                } else {
                    return;
                }
            }
            if (currentPage === 14) { //Set clientLikesCurrentColour JSF variable
                if (typeof argin !== 'undefined') {
                    $('#formColour\\:clientLikesCurrentColour').val(argin);
                } else {
                    return;
                }
            }
            if (currentPage === 15) { //Set clientHasWhiteHair JSF variable
                if (typeof argin !== 'undefined') {
                    $('#formColour\\:clientHasWhiteHair').val(argin);
                } else {
                    return;
                }
            }
            //Increment pageIndex and update the display to the corresponding
            //page in consultation pages.
            pageIndex++;
            currentPage = consultationPages[pageIndex - 2];
            updateDisplay(currentPage);
            $('#nextButton').show();
            $('#previousButton').show();
            //alert(pageIndex + ":" + maxPages);
            if (pageIndex === maxPages) { //Max number of pages
                $('#nextButton').hide();
                $('#finishButton').show();
                $('#formColour\\:clientPhoto').show();
            }
        }
    };
    
    /**
     * Changes the injected code to the previous page.
     * @returns {undefined}
     */
    this.previousPage = function () {
        if (pageIndex > 1) {
            pageIndex--;
            $('#nextButton').show();
            if (pageIndex === 1) { //Max number of pages
                //hide next button.
                $('#nextButton').hide();
                $('#previousButton').hide();
                $('#finishButton').hide();
                $('#formColour\\:clientPhoto').hide();
                $('#formColour\\:imginput').hide();
                currentPage = 1;
            }
            if (pageIndex > 1 && pageIndex < maxPages) {
                currentPage = consultationPages[pageIndex - 2];
                $('#finishButton').hide();
                $('#formColour\\:clientPhoto').hide();
                $('#formColour\\:imginput').hide();
            }
            updateDisplay(currentPage);
        }
    };

    /*
     * Action the submitCConsultation JSF function and
     * redirect to consultation complete page
     */
    this.submitConsultation = function () {
        if ($('#formColour\\:clientPhoto').val() !== "") {
            $('#formColour\\:submitCConsultation').click();
            $(location).attr("href", "../consultation/consultationcomplete.xhtml");
        } else {
            alert("You must select a photo before continuing!");
        }
    };
});