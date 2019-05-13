package com.thing2x.rptsvr.repo.db

import com.thing2x.rptsvr.repo.db.DBSchema._
import slick.jdbc.H2Profile.api._
import slick.lifted.ProvenShape

import scala.concurrent.Future

//     create table JIReportUnitInputControl (
//        report_unit_id number(19,0) not null,
//        input_control_id number(19,0) not null,
//        control_index number(10,0) not null,
//        primary key (report_unit_id, control_index)
//    );
final case class JIReportUnitInputControl ( reportUnitId: Long,
                                            inputControlId: Long,
                                            controlIndex: Int)

final class JIReportUnitInputControlTable(tag: Tag) extends Table[JIReportUnitInputControl](tag, "JIReportUnitInputControl") {
  def reportUnitId = column[Long]("report_unit_id")
  def inputControlId = column[Long]("input_control_id")
  def controlIndex = column[Int]("control_index")

  def * : ProvenShape[JIReportUnitInputControl] = (reportUnitId, inputControlId, controlIndex).mapTo[JIReportUnitInputControl]
}

trait ReportUnitInputControlTableSupport { mySelf: DBRepository =>

  def insertReportUnitInputControl(ctl: JIReportUnitInputControl): Future[Long] = {
    val action = reportUnitInputControls += ctl
    dbContext.run(action).map( _ => ctl.inputControlId )
  }
}