package ru.hogwarts.school.exseption;

public class StudentNotFoundException extends RuntimeException{
        private final long id;

        public StudentNotFoundException(Long id) {
            this.id = id;
        }

        public long getid() {
            return id;
        }
}
