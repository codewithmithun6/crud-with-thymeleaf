<!-- JavaScript to update time -->

    function updateTime() {
        const now = new Date();
        const timeString = now.toLocaleTimeString();
        document.getElementById('current-time').innerText = timeString;
    }

    // Update the time every second
    setInterval(updateTime, 1000);
    updateTime();  // Initial call to display time immediately on page load

            function confirmStudentUpdate() {
                return confirm('Are you sure you want to Update this student?');
            }

            function confirmStudentDelete() {
                return confirm('Are you sure you want to delete this student?');
            }

            function confirmStudentView() {
                return confirm('Are you sure you want to View this student?');
            }

            function confirmTeacherUpdate() {
                return confirm('Are you sure you want to Update this teacher?');
            }

             function confirmTeacherDelete() {
                return confirm('Are you sure you want to delete this teacher?');
             }


