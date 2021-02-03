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
package fr.tezea.chantiers.service;

import fr.tezea.chantiers.domain.school.Professeur;
import fr.tezea.chantiers.domain.school.Student;
import fr.tezea.chantiers.repository.school.ProfesseurRepository;
import fr.tezea.chantiers.repository.school.StudentRepository;
import fr.tezea.chantiers.service.dto.school.StudentDTO;
import fr.tezea.chantiers.service.mapper.school.StudentMapper;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class StudentService
{
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final ProfesseurRepository professeurRepository;
    private final SequenceGeneratorService sequenceGenerator;

    public StudentService(StudentMapper studentMapper, StudentRepository studentRepository,
            ProfesseurRepository professeurRepository, SequenceGeneratorService sequenceGenerator)
    {
        super();
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
        this.professeurRepository = professeurRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public StudentDTO getStudentById(long id)
    {
        Optional<Student> student = this.studentRepository.findById(id);

        if (student.isPresent())
        {
            return this.studentMapper.toStudentDTO(student.get());
        }
        return new StudentDTO();
    }

    public long addStudent(StudentDTO studentDTO)
    {
        Student student = this.studentMapper.toStudent(studentDTO);
        student.setId(sequenceGenerator.generateSequence(Student.SEQUENCE_NAME));
        Optional<Professeur> professeur = this.professeurRepository.findById(studentDTO.getProfesseurId());

        if (professeur.isPresent())
        {
            student.setProf(professeur.get());
        }
        return this.studentRepository.save(student).getId();
    }

    public StudentDTO updateStudentById(long id, StudentDTO studentDTO)
    {
        Optional<Student> student = this.studentRepository.findById(id);

        if (student.isPresent())
        {
            Optional<Professeur> professeur = this.professeurRepository.findById(studentDTO.getProfesseurId());

            if (professeur.isPresent())
            {
                student.get().setProf(professeur.get());
            }
            return this.studentMapper.toStudentDTO(
                    studentRepository.save(this.studentMapper.updateStudentFromDTO(studentDTO, student.get())));
        }
        return new StudentDTO();
    }

    public void deleteStudentById(long id)
    {
        Optional<Student> student = this.studentRepository.findById(id);

        if (student.isPresent())
        {
            this.studentRepository.deleteById(id);
        }
    }
}
