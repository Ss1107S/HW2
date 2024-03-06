package by.orlova.service.impl;

import by.orlova.entity.Department;
import by.orlova.dao.DepartmentDao;
import by.orlova.dao.exception.DaoException;
import by.orlova.dao.impl.DepartmentDaoImpl;
import by.orlova.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl extends DepartmentService {

    private final DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public List<Department> findAll() {
        try {
            return departmentDao.findAll();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Department findEntityById(Integer id) {
        try {
            return departmentDao.findEntityById(id);
        } catch (DaoException e) {
            throw new RuntimeException("Department with id: " + id + " doesn't exist. " + e);
        }
    }

    @Override
    public boolean delete(Department department) {
        try {
            return departmentDao.delete(department);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try {
            return departmentDao.delete(id);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean create(Department department) {
        try {
            return departmentDao.create(department);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Department update(Department department) {
        return null;
    }
}