package io.realworld.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity {
  @Id
  @GeneratedValue
  var id: Long? = null

//  @JsonIgnore
  @Column(updatable = false)
  @CreationTimestamp
  var createAt: LocalDateTime? = null

//  @JsonIgnore
  @UpdateTimestamp
  var updateAt: LocalDateTime? = null
}
