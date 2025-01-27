import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Student } from '../model/student';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private studentUrl: string;

  constructor(private http: HttpClient) {
    this.studentUrl = 'http://localhost:8081/students';
  }

  public findAll(): Observable<Student[]> {
    return this.http.get<Student[]>(this.studentUrl);
  }

  public save(student: Student) {
    return this.http.post<Student>(this.studentUrl, student);
  }

  public getStudentById(id: number): Observable<Student> {
    return this.http.get<Student>(`${this.studentUrl}/${id}`);
  }

  public updateStudent(student: Student): Observable<Student> {
    return this.http.put<Student>(`${this.studentUrl}/${student.id}`, student);
  }
}
