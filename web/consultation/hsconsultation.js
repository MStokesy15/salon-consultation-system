//Execute on page load
$(function () {
    //Variable declaration
    var pageIndex = 1; //page index tracking
    var currentPage = 1; //current page in consultationPages
    var maxPages; //max number of page indices

    //Define pages associated with each consultation type - the same for evaluation
    var consultationTypes = {"Blowdry": [2, 3, 4, 5, 6, 7, 8],
        "Haircut": [2, 3, 4, 5, 6, 7, 8],
        "Hairup": [2, 3, 4, 5, 6, 7, 8],
        "Braid": [2, 3, 4, 5, 6, 7, 8]};
    var consultationPages = [];

    //Load the ColourConsultationWizard html file for loading pages from
    $.get('../consultation/HairstylingConsultationWizard.html', function (data)
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
        $('.clientPhoto').hide();
        $('#consultation').focus();
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
        $('#consultation').load('../consultation/HairstylingConsultationWizard.html #p' + pageNum);
    }

    /**
     * Decides, based on the current page, what action(s) should be taken and
     * what page should be loaded from consultationPages.
     * @param {String} argin Optional argument
     * @returns {undefined}
     */
    this.nextPage = function (argin) {
        if (pageIndex < maxPages || pageIndex === 1) {
            if (currentPage === 1) { //Set the serviceType JSF variable
                if (typeof argin !== 'undefined') {
                    $('#formHairstyling\\:serviceType').val(argin);
                }
                consultationPages = consultationTypes[$('#formHairstyling\\:serviceType').val()];
                maxPages = consultationPages.length + 1;
            }
            if (currentPage === 2) { //Set the appointmentDate JSF variable
                if ($('#aptDate').val() === "") {
                    alert("You must enter an appointment date!");
                    return;
                } else {
                    //console.log($('#aptDate').val())

                    $('#formHairstyling\\:appointmentDate').val($('#aptDate').val());
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
                    $('#formHairstyling\\:clientLikes').val(likesList);
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
                    $('#formHairstyling\\:clientDislikes').val(dislikesList);
                }
            }
            if (currentPage === 5) { //Set the clientHomeStyle JSF variable
                if ($('#homeStyle').val() === "") {
                    return;
                } else {
                    $('#formHairstyling\\:clientHomeStyle').val($('#homeStyle').val());
                }
            }
            if (currentPage === 6) { //Set the clientHomeProducts JSF variable
                if ($('#homeProducts').val() === "") {
                    return;
                } else {
                    $('#formHairstyling\\:clientHomeProducts').val($('#homeProducts').val());
                }
            }
            if (currentPage === 7) { //Set the clientLikesStyles JSF variable
                if ($('#likesStyles').val() === "") {
                    return;
                } else {
                    $('#formHairstyling\\:clientLikesStyles').val($('#likesStyles').val());
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
                $('#formHairstyling\\:clientPhoto').show();
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
                currentPage = 1;
            }
            if (pageIndex > 1 && pageIndex < maxPages) {
                currentPage = consultationPages[pageIndex - 2];
                $('#finishButton').hide();
                $('.clientPhoto').hide();
            }
            updateDisplay(currentPage);
        }
    };

    /*
     * Action the submitHSConsultation JSF function and
     * redirect to consultation complete page
     */    
    this.submitConsultation = function () {
        if ($('#formHairstyling\\:clientPhoto').val() !== "") {
            $('#formHairstyling\\:submitHSConsultation').click();
            $(location).attr("href", "../consultation/consultationcomplete.xhtml");
        } else {
            alert("You must select a photo before continuing!");
        }

    };
});