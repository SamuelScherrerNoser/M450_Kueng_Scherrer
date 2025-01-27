import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentService } from '../service/student.service';
import { Student } from '../model/student';

@Component({
  selector: 'app-edit-student',
  templateUrl: './edit-student.component.html'
})
export class EditStudentComponent implements OnInit {
  student: Student = new Student();
  loading: boolean = true;

  constructor(
    private studentService: StudentService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const studentId = this.route.snapshot.paramMap.get('id');
    if (studentId) {
      this.studentService.getStudentById(+studentId).subscribe((data) => {
        this.student = data;
        this.loading = false;
      });
    }
  }

  onSubmit(): void {
    if (!this.student.id) {
      console.error('Student ID is missing!');
      return;
    }

    this.studentService.updateStudent(this.student).subscribe(() => {
      this.router.navigate(['/students']);
    });
  }
}
