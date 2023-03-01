package io.realworld.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.ColumnDefault
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

  @ColumnDefault("now()")
  @JsonIgnore
  @Column(updatable = false)
  var createAt: LocalDateTime = LocalDateTime.now()

}
