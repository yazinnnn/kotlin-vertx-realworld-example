package io.realworld.entity

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class UserEntity : BaseEntity() {
  var username: String? = null
  @Column(unique = true)
  var email: String? = null
  var bio: String? = "bio"
  var image: String? = "image"
  var password: String? = null
}
