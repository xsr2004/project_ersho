<template>
  <div class="sale" :style="{height:windowHeight+'px'}">
        <h2 style="margin:0;padding:0;line-height:60px;text-align:center;margin-bottom: 20px;">二手拯救世界/西农版</h2>
        <van-form @submit="onSubmit">
            <van-field
                readonly
                v-model="form.type"
                name="GoodType"
                label="商品种类"
                left-icon="apps-o"
                placeholder="点击选择类型"
                @focus="onfocus"
                :rules="[{ required: true, message: '请填写种类' }]"
            />

          <van-action-sheet v-model="showSelectType" :actions="SelectActions" @select="onSelect" />
            <van-field
                v-model="form.name"
                name="商品名字"
                label="商品名字"
                left-icon="edit"
                :rules="[{ required: true, message: '请填写名字' }]"
            />
            <van-field
                v-model="form.price"
                name="价格"
                label="价格"
                placeholder=""
                left-icon="balance-o"
                :rules="[{ required: true, message: '价格要填，单位是人民币' }]"
            />
            <van-field
                v-model="form.address"
                name="地址"
                label="地址"
                is-link
                readonly
                left-icon="location-o"
                placeholder="请选择所在地区"
                :rules="[{ required: true, message: '地址填一下就欧克了' }]"
                @click="showSelectAddress = true"
            />
          <van-popup v-model="showSelectAddress" round position="bottom">
            <van-cascader
                v-model="cascaderValue"
                title="请选择所在地区"
                :options="options"
                active-color="#4fc08d"
                @close="showSelectAddress = false"
                @finish="onFinishSelect"
            />
          </van-popup>
            <van-field
                v-model="form.contactway"
                name="联系方式"
                label="联系方式"
                left-icon="qq"
                :rules="[{ required: true, message: '请填写联系方式' }]"
            />
            <van-field
                v-model="form.description"
                name="描述"
                label="商品描述"
                left-icon="comment-o"
                rows="3"
                autosize
                type="textarea"
                placeholder="写下您的描述吧~~~"
                maxlength="50"
                show-word-limit
            />
            <van-field name="uploader" label="图片" left-icon="video-o">
                <template #input>
                    <van-uploader v-model="form.uploader" :after-read="afterRead" />
                </template>
            </van-field>

            <div style="margin: 16px;">
                <van-button round block type="info" native-type="submit" color="#4fc08d">提交</van-button>
            </div>
        </van-form>
  </div>
</template>

<script>
import {SubmitForm} from "@/api";
import {Notify, Toast,Dialog} from "vant";

export default {
    name:'SaleGood',
    data() {
        return {
            showSelectType:false,//类型动作面板显示
            showSelectAddress:false,//地址popup显示
            SelectActions: [{ name: '宿舍用品' }, { name: '学习作业/资料' }, { name: '自行车/电动车' }],
            cascaderValue:'',
            form:{
                type: '',//种类
                name: '',//名字
                price:'',//价格，这个要转化
                address:'',//地址，将来要拼接
                description:'',//描述
                contactway:'',//联系方式
                uploader:[],//图片
            },
          options: [
            {
              text: '西区',
              value: '西区',
              children: [
                  { text: '1号楼', value: '1' },
                  { text: '2号楼', value: '2' },
                  { text: '3号楼', value: '3' },
                  { text: '4号楼', value: '4' },
                  { text: '5号楼', value: '5' },
                  { text: '6号楼', value: '6' },
                  { text: '7号楼', value: '7' },
                  { text: '8号楼', value: '8'},
                  { text: '9号楼', value: '9'},
                  { text: '10号楼', value: '10' },
                  { text: '11号楼', value: '11' },
                  { text: '12号楼', value: '12' },
                  { text: '13号楼', value: '13' },
                  { text: '14号楼', value: '14' },
                  { text: '15号楼', value: '15' },
                  { text: '右任书院', value: 'yr' },
              ],
            },
            {
              text: '东区',
              value: '东区',
              children: [
                { text: '1号楼', value: '1' },
                { text: '2号楼', value: '2' },
                { text: '3号楼', value: '3' },
                { text: '4号楼', value: '4' },
                { text: '5号楼', value: '5' },
                { text: '6号楼', value: '6' },
                { text: '7号楼', value: '7' },
                { text: '8号楼', value: '8' },
                { text: '9号楼', value: '9' },
                { text: '10号楼', value: '10' },
                { text: '11号楼', value: '11' },
                { text: '12号楼', value: '12' },
              ],
            },

          ],
          windowHeight:document.documentElement.clientHeight-100,
           
        }
    },
    methods:{
      //当商品种类输入框获得焦点后，会让回弹视图显示
      onfocus(){
        this.showSelectType=true
      },
      // 当用户选择一个item后，让回弹视图消失
      onSelect(item){
        this.showSelectType=false;
        this.form.type=item.name
      },
      //
      afterRead(file){
        console.log(file.file)
        let newfile = this.form.uploader;
        console.log(newfile);
      },
      onSubmit(){
          //判断价格
          if(isNaN(Number(this.form.price))){
            Notify({ type: 'warning', message: '检查一下价格格式' })
            return;
          }
          //判断图片
          if(this.form.uploader.length==0){
            Notify({ type: 'warning', message: '请上传图片' })
            return;
          }
          const formData = new FormData();
          formData.append('type', this.form.type);
          formData.append('name', this.form.name);
          formData.append('price', this.form.price);
          formData.append('address', this.form.address);
          formData.append('description', this.form.description);
          formData.append('contactway', this.form.contactway);
          // 如果uploader不存在返回消息break
          for (let i = 0; i < this.form.uploader.length; i++) {
            formData.append('uploader', this.form.uploader[i].file);
          }
          Toast.loading({
            message: '正在上传...',
            forbidClick: true,
            duration:'5000'
          });
          console.log(formData)
          SubmitForm(formData).then(()=>{
            Toast.success('成功文案');
            this.form={}
          }).catch((error)=>{
            Toast.fail('好像失败啦');
          })




      },
      onFinishSelect({ selectedOptions }){
        this.showSelectAddress = false;
        this.form.address = selectedOptions.map((option) => option.text).join('');
      }
    }
}
</script>

<style lang="less" scoped>
    .sale{
        overflow: auto;
        margin-top: 20px;
        /deep/ .van-field__label{
            font-size: 17px;
            line-height: 20px;
            width:70px;
        }
        /deep/ .van-cell{
            padding:20px 20px;
            .van-field__error-message{
              position: absolute;
              top: 0;
              right: 0;
            }
        }
        /deep/ .van-field__control{
            font-size: 20px;
            line-height: 20px;
        }
        /deep/ .van-button--normal{
            font-size: 20px;
        }
    }
</style>