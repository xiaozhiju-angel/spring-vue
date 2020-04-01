import Vue from 'vue'
import Router from 'vue-router'
import Home from  '../views/Home'
import  Login from  '../views/Login';
import  MessageLogin from  '../views/MessageLogin';
import  UserHome from  '../views/user/UserHome';
import  StaffHome from  '../views/staff/StaffHome';
import  AdministerHome from  '../views/administer/AdministerHome';
import  AddAdmin from  '../views/administer/AddAdmin';
import  Swiper from  '../views/Swiper';
import  TimeDate from  '../views/TimeDate';
import  UserRegister from  '../views/UserRegister';
import  UploadImg from  '../views/UploadImg';
import  UploadFile from  '../views/UploadFile';
import  Testupload from  '../components/Testupload';
import  Test from '../views/test/query';
import  Test1 from '../views/test/test1';
import  Video from '../views/video/Video'
import  TestTable from '../views/test/queryUser'
import  ForgetPass from  '../views/ForgetPass';
import  AdminAddUser from '../views/administer/AddUser.vue'
import  AdminQueryUser from '../views/administer/QueryUser.vue'
import  QueryAdmin from '../views/administer/QueryAdmin.vue'
import  RegisterUser from '../views/Register/User.vue'
import  RegisterStaff from '../views/Register/Staff.vue'
import  RegisterAdmin from '../views/Register/Admin.vue'
import  UserInfoDialog from '../views/Dialog/UserInfoDialog.vue'
import  PetRegister from  '../views/pet/PetRegister.vue'
import  Pet from  '../views/pet/Pet.vue'
import  Foster from  '../views/pet/Foster.vue'
import  FosterPet from  '../views/pet/FosterPet.vue'
import  UpdateStaff from  '../views/update/Updatestaff.vue'
import  TestDelete from '../views/test/testdelete.vue'
import  TestRegister from '../views/test/TestRegister.vue'
import  MessageInputLogin from '../views/MessageInputLogin.vue'
import  TestQuery from  '../views/query/testQuery.vue'
import  UPetFoster from '../views/user/UPetFosterApply.vue'
import  UPetFosterRecord from '../views/user/UPetFosterRecord.vue'
import  UPetQuery from '../views/user/UPetQuery.vue'
import  ResetQueryUser from '../views/test/ResetQueryUser.vue'
import  UPetAdoptRecord from '../views/administer/adoptmanage/UPetAdoptRecord.vue'
import  ReplyUPetApply from '../views/administer/petmanage/ReplyUPetApply.vue'
import  LookLosePublish from '../views/administer/searchmanage/LookLosePublish.vue'
import  LookPickupPublish from '../views/administer/searchmanage/LookPickupPublish.vue'
import  LosePublish from '../views/administer/searchmanage/LosePublish.vue'
import  PickupPublish from '../views/administer/searchmanage/PickupPublish.vue'
import  ReplyUPetFoster from '../views/administer/fostermanage/ReplyUPetFoster.vue'
import  Upet from '../views/administer/replypetapply/Upet.vue'
import  QuerySelf from '../views/user/queryuserself/QuerySelf.vue'
import  QueryUserSelf from '../views/user/queryuserself/QuerUserSelf.vue'
import  Connect from '../views/query/Connect.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      redirect:'/home'
    },
    {
      path:'/test1',
      name: 'Test1',
      component: Test1
    },
    {
      path:'/connect',
      name: 'Connect',
      component: Connect
    },
    {
      path:'/qus',
      name: 'QueryUserSelf',
      component: QueryUserSelf
    },
    {
      path:'/qs',
      name: 'QuerySelf',
      component: QuerySelf
    },
    {
      path:'/replayreg',
      name: 'Upet',
      component: Upet
    },
    {
      path:'/testupload',
      name: 'Testupload',
      component: Testupload
    },
    {
      path:'/qad',
      name: 'QueryAdmin',
      component: QueryAdmin
    },
    {
      path:'/addadmin',
      name: 'AddAdmin',
      component: AddAdmin
    },
    {
      path:'/time',
      name: 'TimeDate',
      component: TimeDate
    },
    {
      path:'/swiper',
      name: 'Swiper',
      component: Swiper
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/msg',
      name: 'MessageLogin',
      component: MessageLogin
    },
    {
      path: '/prg',
      name: 'PetRegister',
      component: PetRegister
    },
    {
      path: '/pet',
      name: 'Pet',
      component: Pet
    },
    {
      path: '/fp',
      name: 'FosterPet',
      component: FosterPet
    },
    {
      path: '/foster',
      name: 'Foster',
      component: Foster
    },
    {
      path: '/user',
      name: 'UserHome',
      component: UserHome
    },
    {
      path: '/staff',
      name: 'StaffHome',
      component: StaffHome
    },
    {
      path: '/admin',
      name: 'AdministerHome',
      component: AdministerHome
    },
    {
      path: '/mil',
      name: 'MessageInputLogin',
      component: MessageInputLogin
    },
    {
      path: '/trg',
      name: 'TestRegister',
      component: TestRegister
    },
    {
      path: '/urg',
      name: 'UserRegister',
      component: UserRegister
    },
    {
      path: '/upg',
      name: 'UploadImg',
      component: UploadImg
    },
    {
      path: '/upf',
      name: 'UploadFile',
      component: UploadFile
    },
    {
      path: '/video',
      name: 'Video',
      component: Video
    },
    {
      path: '/fgp',
      name: 'ForgetPass',
      component: ForgetPass
    },
    {
      path: '/adminau',
      name: 'AdminAddUser',
      component: AdminAddUser
    },
    {
      path: '/adminqu',
      name: 'AdminQueryUser',
      component: AdminQueryUser
    },
    {
      path: '/userpartreg',
      name: 'RegisterUser',
      component: RegisterUser
    },
    {
      path: '/staffpartreg',
      name: 'RegisterStaff',
      component: RegisterStaff
    },
    {
      path: '/adminpartreg',
      name: 'RegisterAdmin',
      component: RegisterAdmin
    },
    {
      path: '/uid',
      name: 'UserInfoDialog',
      component: UserInfoDialog
    },
    {
      path: '/test',
      name: 'Test',
      component: Test
    },
    {
      path: '/testtable',
      name: 'TestTable',
      component: TestTable
    },
    {
      path: '/testdelete',
      name: 'TestDelete',
      component: TestDelete
    },

    {
      path: '/us',
      name: 'UpdateStaff',
      component: UpdateStaff
    },
    {
      path: '/tq',
      name: 'TestQuery',
      component: TestQuery
    },
    {
      path: '/ufr',
      name: 'UPetFosterRecord',
      component: UPetFosterRecord
    },
    {
      path: '/uq',
      name: 'UPetQuery',
      component: UPetQuery
    },
    {
      path: '/uf',
      name: 'UPetFoster',
      component: UPetFoster
    },
    {
      path: '/rqu',
      name: 'ResetQueryUser',
      component: ResetQueryUser
    },
    {
      path: '/uar',
      name: 'UPetAdoptRecord',
      component: UPetAdoptRecord
    },
    {
      path: '/rpa',
      name: 'ReplyUPetApply',
      component: ReplyUPetApply
    },
    {
      path: '/llp',
      name: 'LookLosePublish',
      component: LookLosePublish
    },
    {
      path: '/lpp',
      name: 'LookPickupPublish',
      component: LookPickupPublish
    },
    {
      path: '/lp',
      name: 'LosePublish',
      component: LosePublish
    },
    {
      path: '/pp',
      name: 'PickupPublish',
      component: PickupPublish
    },
    {
      path: '/ruf',
      name: 'ReplyUPetFoster',
      component: ReplyUPetFoster
    },
]
})
