<div class="footer">
    <div class="row justify-content-between align-items-center">
        <div class="col">
            <p class="font-size-sm mb-0">
        
                <span class="d-none d-sm-inline-block">.</span>
            </p>
        </div>
        <div class="col-auto">
            <div class="d-flex justify-content-end">
                <!-- List Dot -->
                <ul class="list-inline list-separator">
                    <li class="list-inline-item">
                        <a class="list-separator-link" href="#">FAQ</a>
                    </li>

                    <li class="list-inline-item">
                        <a class="list-separator-link" href="#">License</a>
                    </li>

                    <li class="list-inline-item">
                        <!-- Keyboard Shortcuts Toggle -->
                        <div class="hs-unfold">
                            <a
                                class="js-hs-unfold-invoker btn btn-icon btn-ghost-secondary rounded-circle"
                                href="javascript:;"
                                data-hs-unfold-options='{
                                "target": "#keyboardShortcutsSidebar",
                                "type": "css-animation",
                                "animationIn": "fadeInRight",
                                "animationOut": "fadeOutRight",
                                "hasOverlay": true,
                                "smartPositionOff": true
                                }'
                                >
                                <i class="tio-command-key"></i>
                            </a>
                        </div>
                        <!-- End Keyboard Shortcuts Toggle -->
                    </li>
                </ul>
                <!-- End List Dot -->
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/views/admin/assets\js\demo.js"></script>

<!-- JS Implementing Plugins -->
<script src="${pageContext.request.contextPath}/views/admin/assets\js\vendor.min.js"></script>
<script src="${pageContext.request.contextPath}/views/admin/assets\vendor\chart.js\dist\Chart.min.js"></script>
<script src="${pageContext.request.contextPath}/views/admin/assets\vendor\chartjs-chart-matrix\dist\chartjs-chart-matrix.min.js"></script>

<!-- JS Front -->
<script src="${pageContext.request.contextPath}/views/admin/assets\js\theme.min.js"></script>


<script>
  $(document).on('ready', function () {
    // ONLY DEV
    // =======================================================
    if (window.localStorage.getItem('hs-builder-popover') === null) {
      $('#builderPopover').popover('show')
        .on('shown.bs.popover', function () {
          $('.popover').last().addClass('popover-dark');
        });

      $(document).on('click', '#closeBuilderPopover', function() {
        window.localStorage.setItem('hs-builder-popover', true);
        $('#builderPopover').popover('dispose');
      });
    } else {
      $('#builderPopover').on('show.bs.popover', function () {
        return false;
      });
    }
    // END ONLY DEV
    // =======================================================

    // BUILDER TOGGLE INVOKER
    // =======================================================
    $('.js-navbar-vertical-aside-toggle-invoker').click(function () {
      $('.js-navbar-vertical-aside-toggle-invoker i').tooltip('hide');
    });

    // INITIALIZATION OF MEGA MENU
    // =======================================================
    var megaMenu = new HSMegaMenu($('.js-mega-menu'), {
      desktop: {
        position: 'left'
      }
    }).init();

    // INITIALIZATION OF NAVBAR VERTICAL NAVIGATION
    // =======================================================
    var sidebar = $('.js-navbar-vertical-aside').hsSideNav({
      mobileOverlayClass: 'd-print-none'
    });

    $('.js-navbar-vertical-aside').addClass('d-print-none');

    // INITIALIZATION OF TOOLTIP IN NAVBAR VERTICAL MENU
    // =======================================================
    $('.js-nav-tooltip-link').tooltip({ boundary: 'window' });
    $(".js-nav-tooltip-link").on("show.bs.tooltip", function(e) {
      if (!$("body").hasClass("navbar-vertical-aside-mini-mode")) {
        return false;
      }
    });

    // INITIALIZATION OF UNFOLD
    // =======================================================
    $('.js-hs-unfold-invoker').each(function () {
      var unfold = new HSUnfold($(this)).init();
    });

    // INITIALIZATION OF FORM SEARCH
    // =======================================================
    $('.js-form-search').each(function () {
      new HSFormSearch($(this)).init();
    });

    // INITIALIZATION OF SELECT2
    // =======================================================
    $('.js-select2-custom').each(function () {
      var select2 = $.HSCore.components.HSSelect2.init($(this));
    });

    // INITIALIZATION OF DATATABLES
    // =======================================================
    var datatable = $.HSCore.components.HSDatatables.init($('#datatable'), {
      select: {
        style: 'multi',
        selector: 'td:first-child input[type="checkbox"]',
        classMap: {
          checkAll: '#datatableCheckAll',
          counter: '#datatableCounter',
          counterInfo: '#datatableCounterInfo'
        }
      },
      language: {
        zeroRecords: '<div class="text-center p-4">' +
            '<img class="mb-3" src="./assets/svg/illustrations/sorry.svg" alt="Image Description" style="width: 7rem;">' +
            '<p class="mb-0">No data to show</p>' +
            '</div>'
      },
      paging: true,   // Ensure paging is enabled
      pageLength: 15, // Number of entries per page
      lengthMenu: [10, 15, 20] // Page length options
    });

    // Filtering
    $('.js-datatable-filter').on('change', function() {
      var $this = $(this),
        elVal = $this.val(),
        targetColumnIndex = $this.data('target-column-index');

      datatable.column(targetColumnIndex).search(elVal).draw();
    });

    // Search
    $('#datatableSearch').on('mouseup', function (e) {
      var $input = $(this),
        oldValue = $input.val();

      if (oldValue == "") return;

      setTimeout(function(){
        var newValue = $input.val();

        if (newValue == ""){
          datatable.search('').draw();
        }
      }, 1);
    });

    // Toggle column visibility
    $('#toggleColumn_name').change(function (e) {
      datatable.columns(1).visible(e.target.checked);
    });

    $('#toggleColumn_email').change(function (e) {
      datatable.columns(2).visible(e.target.checked);
    });

    $('#toggleColumn_phone').change(function (e) {
      datatable.columns(3).visible(e.target.checked);
    });

    $('#toggleColumn_country').change(function (e) {
      datatable.columns(4).visible(e.target.checked);
    });

    $('#toggleColumn_account_status').change(function (e) {
      datatable.columns(5).visible(e.target.checked);
    });

    $('#toggleColumn_orders').change(function (e) {
      datatable.columns(6).visible(e.target.checked);
    });

    $('#toggleColumn_total_spent').change(function (e) {
      datatable.columns(7).visible(e.target.checked);
    });

    $('#toggleColumn_last_activity').change(function (e) {
      datatable.columns(8).visible(e.target.checked);
    });

    // INITIALIZATION OF MODAL ON PAGE LOAD
    // =======================================================
    $('#customersGuideModal').modal('show');

    // INITIALIZATION OF DATERANGEPICKER
    // =======================================================
    $(".js-daterangepicker").daterangepicker();

    $(".js-daterangepicker-times").daterangepicker({
      timePicker: true,
      startDate: moment().startOf("hour"),
      endDate: moment().startOf("hour").add(32, "hour"),
      locale: {
        format: "M/DD hh:mm A"
      }
    });

    var start = moment();
    var end = moment();

    function cb(start, end) {
      $("#js-daterangepicker-predefined .js-daterangepicker-predefined-preview")
        .html(start.format("MMM D") + " - " + end.format("MMM D, YYYY"));
    }

    $("#js-daterangepicker-predefined").daterangepicker({
      startDate: start,
      endDate: end,
      ranges: {
        Today: [moment(), moment()],
        Yesterday: [moment().subtract(1, "days"), moment().subtract(1, "days")],
        "Last 7 Days": [moment().subtract(6, "days"), moment()],
        "Last 30 Days": [moment().subtract(29, "days"), moment()],
        "This Month": [moment().startOf("month"), moment().endOf("month")],
        "Last Month": [moment().subtract(1, "month").startOf("month"), moment().subtract(1, "month").endOf("month")]
      }
    }, cb);

    cb(start, end);

    // INITIALIZATION OF CHARTJS
    // =======================================================
    $(".js-chart").each(function () {
      $.HSCore.components.HSChartJS.init($(this));
    });

    // INITIALIZATION OF JVECTORMAP
    // =======================================================
    $(".js-jvectormap").each(function () {
      var jVectorMap = $.HSCore.components.HSJVectorMap.init($(this));
    });
  });
</script>

<!-- IE Support -->
<script>
  if (/MSIE \d|Trident.*rv:/.test(navigator.userAgent))
    document.write('<script src="${pageContext.request.contextPath}/views/admin/./assets/vendor/babel-polyfill/polyfill.min.js"><\/script>');
</script>


</body>
</html>
