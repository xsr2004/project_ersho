// 导入vue
import Vue from 'vue'

// 引入组件
import { Button, Icon } from 'vant'
import { Search } from 'vant';
import { Loading } from 'vant';
import { Form } from 'vant';
import { Field } from 'vant';
import { Uploader } from 'vant';
import { Image as VanImage } from 'vant';
import { ShareSheet } from 'vant';
import { Toast } from 'vant';
import { Cell, CellGroup } from 'vant';
import { NavBar } from 'vant';
import { ImagePreview } from 'vant';
Vue.use(NavBar);
Vue.use(Cell);
Vue.use(CellGroup);
import { Popup } from 'vant';

Vue.use(Popup);
import { Switch } from 'vant';
import { Cascader } from 'vant';
import { Notify } from 'vant';
Vue.use(Cascader);
Vue.use(Switch);
import { Dialog } from 'vant';
Vue.use(Dialog);
import { RadioGroup, Radio } from 'vant';
import { Swipe, SwipeItem } from 'vant';
import { Lazyload } from 'vant';
Vue.use(Notify);
Vue.use(Lazyload);
Vue.use(Swipe);
Vue.use(SwipeItem);
Vue.use(Radio);
Vue.use(RadioGroup);
import { Grid, GridItem } from 'vant';
Vue.use(Toast);
Vue.use(ShareSheet);
Vue.use(Grid);
Vue.use(GridItem);
Vue.use(Icon);
Vue.use(VanImage);
Vue.use(Uploader);
import { Tabbar, TabbarItem } from 'vant';

Vue.use(Tabbar);
Vue.use(TabbarItem);
import { DropdownMenu, DropdownItem } from 'vant';
import { ContactCard } from 'vant';

Vue.use(ContactCard);
Vue.use(DropdownMenu);
Vue.use(DropdownItem);
import { ActionSheet } from 'vant';
import { Card } from 'vant';
import { Tag } from 'vant';
import { PullRefresh } from 'vant';

Vue.use(PullRefresh);
Vue.use(Tag);
Vue.use(Card);
Vue.use(ActionSheet);
Vue.use(Form);
Vue.use(Field);
import { SwipeCell } from 'vant';

Vue.use(SwipeCell);
Vue.use(Loading);

// 使用组件
Vue.use(Button)
Vue.use(Icon)
Vue.use(Search);
