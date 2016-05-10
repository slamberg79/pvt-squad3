var CourseList = React.createClass({
    printVideo : function() {
        ReactDOM.render(<AssignmentContent course="kurs 3" assignment="assignment 2"/>,document.getElementById("courseContent"));
    },
    
    studentSubmitions: function () {
        /* $.get("/db/getAllSubmitions",{value:1000},function (res) {
         var users = JSON.parse(res);
         console.log(res);
         console.log(users);
         */
        var users = [{
            assignmentID: 1000,
            studentID: 21,
            studentName: "Anton Andersson",
            submitionDate: "2010-06-23",
            grade: "G",
            teacherID: 12
        },
            {
                assignmentID: 1000,
                studentID: 21,
                studentName: "Lukas Lundberg",
                submitionDate: "2010-06-23",
                grade: "VG",
                teacherID: 12
            }];
        ReactDOM.render(<StudentList students={users}/>, document.getElementById('courseContent'));
    },

    printFeedback : function() {
        ReactDOM.render(<Feedback />,document.getElementById("courseContent"));
    },

    newAssignment : function() {
        ReactDOM.render(<NewAssignment />, document.getElementById('courseContent'));
    },
    teacherRecordVideo : function() {
        ReactDOM.render(<TeacherRecordVideo />, document.getElementById('courseContent'));
    },
    studentRecordVideo : function() {
            ReactDOM.render(<StudentRecordVideo />, document.getElementById('courseContent'));
        },
	render : function() {
		var listComponents = [];
        listComponents.push(<div className="menuItem" key={1} onClick={this.printVideo} ><h6 className="navigationText">Video Playback</h6></div>);
        listComponents.push(<div className="menuItem" key={2} onClick={this.printFeedback}><h6 className="navigationText">Feedback</h6></div>);
        listComponents.push(<div className="menuItem" key={3} onClick={this.studentSubmitions}><h6 className="navigationText">Teacher view assignment</h6></div>);
        listComponents.push(<div className="menuItem" key={4} onClick={this.newAssignment}><h6 className="navigationText">Teacher new assignment</h6></div>);
        listComponents.push(<div className="menuItem" key={5} onClick={this.teacherRecordVideo}><h6 className="navigationText">Teacher Record Video</h6></div>);
        listComponents.push(<div className="menuItem" key={6} onClick={this.studentRecordVideo}><h6 className="navigationText">Student Record Video</h6></div>);

		return <div>{listComponents}</div>;
	}
});
ReactDOM.render(<CourseList />, document.getElementById('desktopNavigation')); 
