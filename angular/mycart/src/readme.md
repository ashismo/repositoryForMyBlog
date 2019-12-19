1. Interpolation: {{}}
2. Property binding []
  <a [title]="product.name+ ' details'">
3. Event binding
  <button (click)="share()">
4. *ngIf and *ngFor : Structural directive
5. @Input() decorator:
  It indicates that property value passes in from the product list component to the child component product-alert 
6. @Output() notify = new EventEmitter();
  The child component emits an event to the parent component
7. Routing: Angular router enables you to navigate from one view to another.
  A. Add routing path in the app.module.ts

@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      { path: '', component: ProductListComponent },
      { path: 'products/:productId', component: ProductDetailsComponent },
    ])
  ],


  B. Use RouterLink directive
 <a [title]="product.name + ' details'" [routerLink]="['/products', product.productId]">

 c. Inject ActivatedRoute into the constructure.
 The ActivatedRoute is specific to each routed component loaded by the Angular Router. It contains information about the route, its parameters, and additional data associated with the route

 8. HttpClient: 
 Angular's HttpClientModule registers the providers needed to use a single instance of the HttpClient service throughout your app. The HttpClient service is what you inject into your services to fetch data and interact with external APIs and resources. 

 9. FormBuilder 
 This is used to control form.
 name and address fieldsvare in a group so they can be reset like this:  this.checkoutForm.reset();
 this.checkoutForm = this.formBuilder.group({
      name: '',
      address: ''
    });
