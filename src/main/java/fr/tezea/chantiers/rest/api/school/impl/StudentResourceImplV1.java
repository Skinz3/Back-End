/*
 * MIT License
 *
 * Copyright (c) 2021 TEZEA-Chantiers
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package fr.tezea.chantiers.rest.api.school.impl;

import fr.tezea.chantiers.rest.api.school.api.StudentResourceV1;
import fr.tezea.chantiers.service.StudentService;
import fr.tezea.chantiers.service.dto.school.StudentDTO;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentResourceImplV1 implements StudentResourceV1
{
    private final StudentService studentService;

    public StudentResourceImplV1(StudentService studentService)
    {
        super();
        this.studentService = studentService;
    }

    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") long id)
    {
        return ResponseEntity.ok(this.studentService.getStudentById(id));
    }

    public ResponseEntity<URI> addStudent(@RequestBody StudentDTO studentDTO)
    {
        URI location = URI.create(String.format("/get/%s", this.studentService.addStudent(studentDTO)));
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<StudentDTO> updateStudentById(@PathVariable("id") long id, @RequestBody StudentDTO studentDTO)
    {
        return ResponseEntity.ok(this.studentService.updateStudentById(id, studentDTO));
    }

    public ResponseEntity<Void> deleteStudentById(@PathVariable("id") long id)
    {
        this.studentService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
