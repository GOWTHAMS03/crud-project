package com.gowtham.crudprojectspring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gowtham.crudprojectspring.Model.User;
import com.gowtham.crudprojectspring.Repository.UserRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepo userrepo;

    @Autowired
    public UserController(UserRepo userrepo) {
        this.userrepo = userrepo;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> listCrud() {
        List<User> crudList = userrepo.findAll();
        return new ResponseEntity<>(crudList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getCrud(@PathVariable Long id) {
        return userrepo.findById(id)
                .map(crud -> new ResponseEntity<>(crud, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<User> createCrud(@RequestBody User crud) {
        User savedCrud = userrepo.save(crud);
        return new ResponseEntity<>(savedCrud, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editCrud(@PathVariable Long id, @RequestBody User updatedCrud) {
        return userrepo.findById(id)
                .map(existingCrud -> {
                    existingCrud.setName(updatedCrud.getName());
                    existingCrud.setAge(updatedCrud.getAge());
                    existingCrud.setEmp_id(updatedCrud.getEmp_id());
                    User savedCrud = userrepo.save(existingCrud);
                    return new ResponseEntity<>(savedCrud, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrud(@PathVariable Long id) {
        if (userrepo.existsById(id)) {
            userrepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
