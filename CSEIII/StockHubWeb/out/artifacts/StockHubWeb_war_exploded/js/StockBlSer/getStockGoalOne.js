/**
 * Created by chenjin on 2017/5/17.
 */
function JSGoalVO(total,stability,prof, mobility,safety, development) {
    this.total=total;
    this.stability=stability;
    this.prof=prof;
    this.mobility=mobility;
    this.safety=safety;
    this.development=development;
    this.showTotal=function () {
        return total;
    }
    this.showStability=function () {
        return stability;
    }
    this.showProf=function () {
        return prof;
    }
    this.showMobility=function () {
        return mobility;
    }
    this.showSafety=function () {
        return safety;
    }
    this.showDevelopment=function () {
        return development;
    }
}

function JSgetStockGoalOne(date,codeOrName) {
    this.date=date;
    this.codeOrName=codeOrName;
    this.showDate=function () {
        return date;
    }
    this.showCodeOrName=function () {
        return codeOrName;
    }
    this.retu=function () {

        return new JSGoalVO(total,stability,prof, mobility,safety, development)
    }
}