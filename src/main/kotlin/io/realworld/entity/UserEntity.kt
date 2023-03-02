package io.realworld.entity

import javax.persistence.Entity

@Entity
class UserEntity: BaseEntity() {
  var username: String? = null
  var email: String? = null
  var bio: String? = null
  var image: String? = null
  var password: String? = null

}
