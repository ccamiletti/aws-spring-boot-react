package nl.cc.task.model;

import lombok.Getter;
import lombok.Setter;
import nl.cc.task.entity.CustomUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserDetailsDTO implements UserDetails {

    private String userName;
    private String password;
    private Boolean active;
    private List<GrantedAuthority> authorities;

    public UserDetailsDTO(CustomUser customUser) {
        this.userName = customUser.getUserName();
        this.password = customUser.getPassword();
        this.active = customUser.getActive();
        //this.authorities = customUser.get
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
