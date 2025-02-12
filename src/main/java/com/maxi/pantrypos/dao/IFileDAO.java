package com.maxi.pantrypos.dao;

import com.maxi.pantrypos.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IFileDAO extends JpaRepository<File, UUID> {
}
