package aero.tav.tams.reference.manager.dal.rdbms.entity.#package#;

import aero.tav.tams.reference.manager.dal.rdbms.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Where(clause = "IS_DELETED = 0")
@SQLDelete(sql = "update #class# SET is_deleted = 1 where id = ?")
@Data
@Entity(name = "#class#")
@Table(name = "#table#")
public class #class#Entity extends BaseEntity {

    @Id
    @SequenceGenerator(name = "GEN_#table#", sequenceName = "SEQ_#table#", allocationSize = 1)
    @GeneratedValue(generator = "GEN_#table#", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


}
